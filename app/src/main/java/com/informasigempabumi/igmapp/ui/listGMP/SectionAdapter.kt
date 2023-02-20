package com.informasigempabumi.igmapp.ui.listGMP

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.informasigempabumi.igmapp.ui.listGMP.dirasakan.ListDiRasakanFragment
import com.informasigempabumi.igmapp.ui.listGMP.terkini.ListTerkiniFragment

class SectionAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> {
                fragment = ListTerkiniFragment()
            }
            1 -> {
                fragment = ListDiRasakanFragment()
            }
        }
        return fragment as Fragment
    }
}