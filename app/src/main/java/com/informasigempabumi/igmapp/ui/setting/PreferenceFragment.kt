package com.informasigempabumi.igmapp.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.informasigempabumi.igmapp.R

class PreferenceFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preference_layout)
    }
}