package com.informasigempabumi.igmapp.core.utils

import com.mapbox.mapboxsdk.geometry.LatLng

object ParsingDataCoordinateToLatLong {
    fun parsing(cordinate: String): LatLng {
        val coordinates = cordinate
        val parts = coordinates.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val latitude = parts[0].toDouble()
        val longitude = parts[1].toDouble()
        return LatLng(latitude, longitude)
    }
}