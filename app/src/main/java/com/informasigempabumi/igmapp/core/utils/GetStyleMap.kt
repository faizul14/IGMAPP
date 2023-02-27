package com.informasigempabumi.igmapp.core.utils

import android.content.Context
import androidx.preference.PreferenceManager
import com.informasigempabumi.igmapp.R
import com.mapbox.mapboxsdk.maps.Style

object GetStyleMap {
     fun getPreferenceMapStyle(context: Context): String {
        val preference = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        val mapStyle = preference.getString(
            context.resources.getString(R.string.pref_key_map_style),
            context.resources.getString(R.string.pref_map_entry_streets)
        )
        if (mapStyle != null) {
            return when (mapStyle) {
                context.resources.getString(R.string.pref_map_entry_outdoors) -> Style.OUTDOORS
                context.resources.getString(R.string.pref_map_entry_satellite) -> Style.SATELLITE
                context.resources.getString(R.string.pref_map_entry_satellite_streets) -> Style.SATELLITE_STREETS
                context.resources.getString(R.string.pref_map_entry_light) -> Style.LIGHT
                context.resources.getString(R.string.pref_map_entry_dark) -> Style.DARK
                context.resources.getString(R.string.pref_map_entry_traffic_day) -> Style.TRAFFIC_DAY
                context.resources.getString(R.string.pref_map_entry_traffic_night) -> Style.TRAFFIC_NIGHT
                else -> Style.MAPBOX_STREETS
            }
        }
        return Style.MAPBOX_STREETS
    }
}