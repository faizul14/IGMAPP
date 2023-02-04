package com.informasigempabumi.igmapp.ui.detailGMP

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.informasigempabumi.igmapp.R

class DetailgmpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailgmp)
        supportActionBar?.hide()
    }
}