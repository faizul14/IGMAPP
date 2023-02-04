package com.informasigempabumi.igmapp.ui.listGMP

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        // Inflate the layout for this fragment
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}