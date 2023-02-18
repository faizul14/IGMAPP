package com.informasigempabumi.igmapp.ui.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.informasigempabumi.igmapp.R
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.utils.ParsingDataCoordinateToLatLong
import com.informasigempabumi.igmapp.core.utils.ViewModelFactory
import com.informasigempabumi.igmapp.databinding.FragmentHomeBinding
import com.informasigempabumi.igmapp.ui.detailGMP.DetailgmpActivity
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mapView: MapView
    private lateinit var mapboxMap: MapboxMap
    private lateinit var symbolManager: SymbolManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = ViewModelFactory.getInstance()
        val homeViewModel =
            ViewModelProvider(requireActivity(), factory).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //mapview
        mapView = _binding!!.mapViewOnHome
        mapView.onCreate(savedInstanceState)
        homeViewModel.getGempaLive().observe(requireActivity()) { data ->
            display(data)
        }
        return root
    }

    private fun display(dataGempa: DataGempa) {
        val dataMag = dataGempa.magnitude!!.toDouble()
        _binding?.include?.apply {
            tvHomeMagnitudo.text = "${dataMag} SR"
            when (dataMag) {
                in 0.0..4.9 -> {
                    materialCardView.setBackgroundDrawable(
                        ContextCompat.getDrawable(binding.root.context, R.drawable.bg_circle_green)
                    )
                }
                in 5.0..6.0 -> {
                    materialCardView.setBackgroundDrawable(
                        ContextCompat.getDrawable(binding.root.context, R.drawable.bg_circle_yellow)
                    )
                }
                else -> {
                    materialCardView.setBackgroundDrawable(
                        ContextCompat.getDrawable(binding.root.context, R.drawable.bg_circle_red)
                    )
                }
            }
            tvTglJam.setText("${dataGempa.tanggal}\n${dataGempa.jam}")
            tvPusatGempa.setText(dataGempa.wilayah)
            tvDirasakan.setText(dataGempa.dirasakan)
            tvDetailKedalaman.setText(dataGempa.kedalaman)
            tvPotensi.setText(dataGempa.potensi)
        }

        //set MapBox
        mapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
                symbolManager = SymbolManager(mapView, mapboxMap, style)
                symbolManager.iconAllowOverlap = true

                style.addImage(
                    DetailgmpActivity.ID,
                    BitmapFactory.decodeResource(resources, R.drawable.ic_gempa_marker)
                )

                dataGempa.coordinates?.let { showMarker(it) }
            }
        }
    }

    private fun showMarker(location: String) {
        val latLong = LatLng(ParsingDataCoordinateToLatLong.parsing(location))
        symbolManager.create(
            SymbolOptions().withLatLng(LatLng(latLong.latitude, latLong.longitude))
                .withIconImage(DetailgmpActivity.ID).withIconSize(1.0f)
                .withIconOffset(arrayOf(0f, -1.5f))
                .withTextField("Location $location").withTextHaloColor("rgba(255, 255, 255, 100)")
                .withTextHaloWidth(5.0f).withTextAnchor("top").withTextOffset(arrayOf(0f, 2.5f))
                .withDraggable(false)

        )
        mapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong, 6.0))

    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}