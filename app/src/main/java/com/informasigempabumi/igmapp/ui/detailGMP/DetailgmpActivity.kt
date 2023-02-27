package com.informasigempabumi.igmapp.ui.detailGMP

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.informasigempabumi.igmapp.R
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.utils.FormatStyleWilayah
import com.informasigempabumi.igmapp.core.utils.GetStyleMap
import com.informasigempabumi.igmapp.core.utils.ParsingDataCoordinateToLatLong
import com.informasigempabumi.igmapp.core.utils.Resultmagnitudeimpact
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
    private var dataTerkiniOrDirasakan: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailgmpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        dataGempa = intent.getParcelableExtra<DataGempa>(EXTRA_DATA) as DataGempa
        dataTerkiniOrDirasakan = intent.getStringExtra(ID_TERKIN_OR_DIRASAKAN)
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        display()
        btnClick()


    }

    private fun display() {
        val dataMag = dataGempa.magnitude?.toDouble()
        binding.icdBottomDetail.apply {
            when (dataMag!!) {
                in 0.0..4.9 -> {
                    materialCardView.setBackgroundDrawable(
                        ContextCompat.getDrawable(binding.root.context, R.drawable.bg_circle_green)
                    )
                    statusViewSide.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context, R.color.green
                        )
                    )
                }
                in 5.0..6.0 -> {
                    materialCardView.setBackgroundDrawable(
                        ContextCompat.getDrawable(binding.root.context, R.drawable.bg_circle_yellow)
                    )
                    statusViewSide.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context, R.color.yelow
                        )
                    )
                }
                else -> {
                    materialCardView.setBackgroundDrawable(
                        ContextCompat.getDrawable(binding.root.context, R.drawable.bg_circle_red)
                    )
                    statusViewSide.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context, R.color.read
                        )
                    )
                }
            }
            when (dataTerkiniOrDirasakan) {
                "1" -> {
                    tvTittlePotensiOrDirasakan.text = "Dirasakan"
                    tvDetailPotensi.text = dataGempa.dirasakan
                    binding.tvDetailWilayah.text = dataGempa.wilayah
                    binding.icdBottomDetail.tvSumber.text = resources.getString(R.string.sumber1)

                }
                else -> {
                    tvDetailPotensi.text = dataGempa.potensi
                    binding.icdBottomDetail.tvSumber.text = resources.getString(R.string.sumber0)
                    binding.tvDetailWilayah.text = dataGempa.wilayah?.let {
                        FormatStyleWilayah.getLastWordInNewLine(
                            it
                        )
                    }

                }
            }
            tvDetailDampakMag.text =
                Resultmagnitudeimpact.resultImpact(dataMag, this@DetailgmpActivity)
            tvDetailMagnitudo.text = "$dataMag SR"
            tvDetailMagnitudo2.text = "$dataMag SR"
            tvDetailWaktu2.text = "${dataGempa.tanggal}\n${dataGempa.jam}"
            tvDetailKedalaman2.text = dataGempa.kedalaman
            tvDetailKordinat.text = dataGempa.coordinates
        }

        //set mapbox
        mapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            mapboxMap.setStyle(GetStyleMap.getPreferenceMapStyle(this.applicationContext)) { style ->
                symbolManager = SymbolManager(mapView, mapboxMap, style)
                symbolManager.iconAllowOverlap = true

                style.addImage(
                    ID, BitmapFactory.decodeResource(resources, R.drawable.ic_gempa_marker)
                )

                dataGempa.coordinates?.let { showMarker(it) }
            }
        }
    }

    private fun btnClick() {
        binding.icdBottomDetail.lrSumber.setOnClickListener {
            moveToWeb(binding.icdBottomDetail.tvSumber.text.toString())
        }
        binding.icdBottomDetail.lrDampak.setOnClickListener {
            moveToWeb(getString(R.string.url_wikipedia))
        }
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }

    }

    private fun moveToWeb(url: String) {
        val moveWeb = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(moveWeb)
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
        mapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong, 5.0))

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
        const val ID_TERKIN_OR_DIRASAKAN = "data"
    }

}