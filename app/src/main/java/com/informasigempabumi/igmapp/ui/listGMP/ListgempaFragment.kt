package com.informasigempabumi.igmapp.ui.listGMP

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.informasigempabumi.igmapp.R
import com.informasigempabumi.igmapp.databinding.FragmentListgempaBinding


class ListgempaFragment : Fragment() {

    private var _binding: FragmentListgempaBinding? = null
    private val binding
        get() =
            _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListgempaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewPagerDisplayed()
        // Inflate the layout for this fragment
        return root
    }

    private fun viewPagerDisplayed(){
        val sectionPagerAdapter = SectionAdapter(requireActivity() as AppCompatActivity)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager){tab, position->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )

        private val TAB_ICON = intArrayOf(
            R.drawable.ic_waktu,
            R.drawable.ic_kedalaman
        )
    }

}