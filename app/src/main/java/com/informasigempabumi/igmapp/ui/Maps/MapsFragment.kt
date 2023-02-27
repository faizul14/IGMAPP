package com.informasigempabumi.igmapp.ui.Maps

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.informasigempabumi.igmapp.R
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.utils.GetStyleMap
import com.informasigempabumi.igmapp.databinding.FragmentMapsBinding
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import org.koin.android.viewmodel.ext.android.viewModel


class MapsFragment : Fragment() {
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mapView: MapView
    private lateinit var mapboxMap: MapboxMap
    private lateinit var symbolManager: SymbolManager
    private val viewModel: MapsViewodel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //mapview
        mapView = _binding!!.mapViewOnHome
        mapView.onCreate(savedInstanceState)
        viewModel.getDataCombine().observe(requireActivity()) { data ->
            display2(data)
        }
        return root
    }


    private fun display2(dataGMP: List<DataGempa>) {
        //set MapBox
        mapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            mapboxMap.setStyle(GetStyleMap.getPreferenceMapStyle(requireContext().applicationContext)) { style ->
                symbolManager = SymbolManager(mapView, mapboxMap, style)
                symbolManager.iconAllowOverlap = true

                style.addImage(
                    ID, BitmapFactory.decodeResource(resources, R.drawable.ic_gempa_marker)
                )

                val symbolLayerId = "symbol-layer-id"
                val symbolLayer = SymbolLayer(symbolLayerId, "source-id")
                symbolLayer.withProperties(
                    PropertyFactory.iconImage(ID),
                    PropertyFactory.iconSize(0.8f),
                    PropertyFactory.iconAllowOverlap(true),
                    PropertyFactory.iconIgnorePlacement(true),
                )
                style.addLayer(symbolLayer)

                val featureList = mutableListOf<Feature>()
                val latLngList = dataGMP.map { dataGempa ->
                    val latLngString = dataGempa.coordinates
                    val latLngArray = latLngString?.split(",")?.map { it.toDouble() }
                    if (latLngArray?.size == 2) {
                        LatLng(latLngArray[0], latLngArray[1])
                    } else {
                        null
                    }
                }.filterNotNull()

                for (latLng in latLngList) {
                    featureList.add(
                        Feature.fromGeometry(
                            Point.fromLngLat(
                                latLng.longitude, latLng.latitude
                            )
                        )
                    )
                }
                val featureCollection = FeatureCollection.fromFeatures(featureList)

                symbolManager.addClickListener { clickedSymbol ->
                    val feature = clickedSymbol.data as Feature
                    val lat = (feature.geometry() as Point).latitude()
                    val lon = (feature.geometry() as Point).longitude()
                    val dataGempa = dataGMP.find {
                        it.coordinates?.contains("$lon,$lat") ?: false
                    }
                    val message = dataGempa?.toString() ?: "No data available"
                    Log.d("Mapbox", "Marker clicked at lat=$lat, lon=$lon")
                    true
                }


                val sourceId = "source-id"
                val source = GeoJsonSource(sourceId, featureCollection)
                style.addSource(source)

                // Buat posisi kamera yang mengarah ke tengah-tengah dari seluruh marker
                val bounds = LatLngBounds.Builder().includes(latLngList).build()
                val cameraPosition = mapboxMap.getCameraForLatLngBounds(bounds)
                val zoomLevel = (cameraPosition?.zoom ?: 0.6) - 0.6
                // Animasikan perpindahan kamera ke posisi tersebut
                if (cameraPosition != null) {
                    val newCameraPosition = CameraPosition.Builder(cameraPosition).zoom(3.0).build()
                    mapboxMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(newCameraPosition), 2000
                    )

                }
            }
        }
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

    companion object {
        const val ID = "1"
    }


}