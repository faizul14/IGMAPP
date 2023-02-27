package com.informasigempabumi.igmapp.ui.setting

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.informasigempabumi.igmapp.R
import com.informasigempabumi.igmapp.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private var binding: FragmentSettingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .add(R.id.container, PreferenceFragment())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}