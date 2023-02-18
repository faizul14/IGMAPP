package com.informasigempabumi.igmapp.ui.detailGMP

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.informasigempabumi.igmapp.R
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.utils.ParsingDataCoordinateToLatLong
import com.informasigempabumi.igmapp.databinding.ActivityDetailgmpBinding
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions

class DetailgmpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailgmpBinding
    private lateinit var mapView: MapView
    private lateinit var mapboxMap: MapboxMap
    private lateinit var symbolManager: SymbolManager
    private lateinit var dataGempa: DataGempa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailgmpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        dataGempa = intent.getParcelableExtra<DataGempa>(EXTRA_DATA) as DataGempa
        binding.tvDetailWilayah.setText(dataGempa.wilayah)
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }


        mapView = findViewById(R.id.mapView)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
                symbolManager = SymbolManager(mapView, mapboxMap, style)
                symbolManager.iconAllowOverlap = true

                style.addImage(
                    ID, BitmapFactory.decodeResource(resources, R.drawable.ic_gempa_marker)
                )

                dataGempa.coordinates?.let { showMarker(it) }
            }
        }

    }

    private fun showMarker(location: String) {
//        val latLong = LatLng(-6.8957643, 107.6338462)
        val latLong = LatLng(ParsingDataCoordinateToLatLong.parsing(location))
        symbolManager.create(
            SymbolOptions().withLatLng(LatLng(latLong.latitude, latLong.longitude))
                .withIconImage(ID).withIconSize(1.0f).withIconOffset(arrayOf(0f, -1.5f))
                .withTextField("Location $location").withTextHaloColor("rgba(255, 255, 255, 100)")
                .withTextHaloWidth(5.0f).withTextAnchor("top").withTextOffset(arrayOf(0f, 2.5f))
                .withDraggable(false)

        )
        mapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong, 8.0))

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

    companion object {
        const val ID = "CONST_ID"
        const val EXTRA_DATA = "extra_data"
    }

}