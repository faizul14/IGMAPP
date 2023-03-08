package com.informasigempabumi.igmapp.ui.home

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.informasigempabumi.igmapp.R
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.utils.GetStyleMap
import com.informasigempabumi.igmapp.core.utils.ParsingDataCoordinateToLatLong
import com.informasigempabumi.igmapp.databinding.FragmentHomeBinding
import com.informasigempabumi.igmapp.ui.detailGMP.DetailgmpActivity
import com.informasigempabumi.igmapp.ui.shakemap.ShakeMapActivity
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mapView: MapView
    private lateinit var mapboxMap: MapboxMap
    private lateinit var symbolManager: SymbolManager
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
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
                in zero..fournine -> {
                    materialCardView.setBackgroundDrawable(
                        ContextCompat.getDrawable(binding.root.context, R.drawable.bg_circle_green)
                    )
                }
                in five..six -> {
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
            tvTglJam.text = "${dataGempa.tanggal}\n${dataGempa.jam}"
            tvPusatGempa.text = dataGempa.wilayah
            tvDirasakan.text = dataGempa.dirasakan
            tvDetailKedalaman.text = dataGempa.kedalaman
            tvPotensi.text = dataGempa.potensi

            //forToShakeMap
            btnToShakemap.setOnClickListener {
                dataGempa.shakemap?.let { it1 -> moveToShakeMap(it1) }
            }
        }

        //set MapBox
        mapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            mapboxMap.setStyle(GetStyleMap.getPreferenceMapStyle(requireContext().applicationContext)) { style ->
                symbolManager = SymbolManager(mapView, mapboxMap, style)
                symbolManager.iconAllowOverlap = true

                style.addImage(
                    DetailgmpActivity.ID,
                    BitmapFactory.decodeResource(resources, R.drawable.ic_gempa_marker)
                )

//                dataGempa.coordinates?.let { showMarker(it) }
                dataGempa.coordinates?.let { dataGempa.wilayah?.let { it1 -> showMarker(it, it1) } }
            }
        }
    }

    private fun showMarker(location: String, wilayah: String) {
        val latLong = LatLng(ParsingDataCoordinateToLatLong.parsing(location))
        symbolManager.create(
            SymbolOptions().withLatLng(LatLng(latLong.latitude, latLong.longitude))
                .withIconImage(DetailgmpActivity.ID).withIconSize(1.0f)
                .withIconOffset(arrayOf(0f, -1.5f)).withTextField("Loksasi $wilayah")
                .withTextHaloColor("rgba(255, 255, 255, 100)").withTextHaloWidth(5.0f)
                .withTextAnchor("top").withTextOffset(arrayOf(0f, 2.5f)).withDraggable(false)

        )
        symbolManager.addClickListener { symbol ->
            if (symbol.iconImage == DetailgmpActivity.ID) {
                // Tambahkan aksi yang ingin dilakukan ketika marker di klik di sini
                // Contoh:
                Toast.makeText(requireContext(), "Marker di-klik!", Toast.LENGTH_SHORT).show()
            }
        }
        mapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong, 5.0))

    }

    private fun moveToShakeMap(idShakeMap: String) {
        val move = Intent(requireActivity(), ShakeMapActivity::class.java)
        move.putExtra(ShakeMapActivity.EXTRA_LINK_SHAKEMAP, idShakeMap)
        startActivity(move)
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

    companion object{
        const val zero = 0.0
        const val fournine = 4.9
        const val five = 5.0
        const val six = 6.0
    }
}