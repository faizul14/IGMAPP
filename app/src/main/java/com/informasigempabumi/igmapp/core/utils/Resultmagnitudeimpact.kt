package com.informasigempabumi.igmapp.core.utils

import android.content.Context
import com.informasigempabumi.igmapp.R

object Resultmagnitudeimpact {
    fun resultImpact(mag: Double, context: Context): String {
        var result: String? = ""
        when (mag) {
            in 0.0..1.9 -> {
                result = context.getString(R.string.above_0_below_2)
            }
            in 2.0..2.9 -> {
                result = context.getString(R.string.above_2_below_29)
            }
            in 3.0..3.9 -> {
                result = context.getString(R.string.above_3_below_39)
            }
            in 4.0..4.9 -> {
                result = context.getString(R.string.above_4_below_49)
            }
            in 5.0..5.9 -> {
                result = context.getString(R.string.above_5_below_59)
            }
            in 6.0..6.9 -> {
                result = context.getString(R.string.above_6_below_69)
            }
            in 7.0..7.9 -> {
                result = context.getString(R.string.above_7_below_79)
            }
            in 8.0..8.9 -> {
                result = context.getString(R.string.above_8_below_89)
            }
            in 9.0..9.9 -> {
                result = context.getString(R.string.above_9_below_99)
            }
            in 10.0..10.9 -> {
                result = context.getString(R.string.above_10_below_19)
            }
            in 11.0..11.9 -> {
                result = context.getString(R.string.above_11_below_119)
            }
            in 12.0..12.9 -> {
                result = context.getString(R.string.above_12_below_129)
            }
            else -> {
                result = context.getString(R.string.over_13)
            }
        }
        return result
    }
}