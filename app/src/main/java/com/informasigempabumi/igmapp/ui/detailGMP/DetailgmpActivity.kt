package com.informasigempabumi.igmapp.ui.detailGMP

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.informasigempabumi.igmapp.R
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailgmpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mapView = findViewById(R.id.mapView)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
                symbolManager = SymbolManager(mapView, mapboxMap, style)
                symbolManager.iconAllowOverlap = true

//                style.addImage(
//                    ID, BitmapFactory.decodeResource(resources, R.drawable.marker_test)
//                )

                showMarker()
            }
        }

    }

    private fun showMarker() {
        val latLong = LatLng(-6.8957643, 107.6338462)
        symbolManager.create(
            SymbolOptions().withLatLng(LatLng(latLong.latitude, latLong.longitude))
                .withIconImage(ID).withIconSize(1.5f).withIconOffset(arrayOf(0f, -1.5f))
                .withTextField("Location Space").withTextHaloColor("rgba(255, 255, 255, 100)")
                .withTextHaloWidth(5.0f).withTextAnchor("top").withTextOffset(arrayOf(0f, 1.5f))
                .withDraggable(true)
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
    }

}