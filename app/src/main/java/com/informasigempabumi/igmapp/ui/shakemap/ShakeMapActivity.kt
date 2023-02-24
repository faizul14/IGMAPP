package com.informasigempabumi.igmapp.ui.shakemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.informasigempabumi.igmapp.BuildConfig
import com.informasigempabumi.igmapp.databinding.ActivityShakeMapBinding

class ShakeMapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShakeMapBinding
    private lateinit var dataShake: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShakeMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        dataShake = intent.getStringExtra(EXTRA_LINK_SHAKEMAP)!!
        display()
    }

    private fun display(){
        binding.apply {
            Glide.with(this@ShakeMapActivity)
                .load(BuildConfig.BASE_URL+dataShake)
                .into(tivShakeMap)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    companion object{
        const val EXTRA_LINK_SHAKEMAP = "extra_link_shakemap"
    }
}