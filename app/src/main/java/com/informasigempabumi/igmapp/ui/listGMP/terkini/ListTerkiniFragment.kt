package com.informasigempabumi.igmapp.ui.listGMP.terkini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.informasigempabumi.igmapp.core.ui.ListTerkiniAdapter
import com.informasigempabumi.igmapp.core.utils.ViewModelFactory
import com.informasigempabumi.igmapp.databinding.FragmentListTerkiniBinding

class ListTerkiniFragment : Fragment() {

    private var _binding: FragmentListTerkiniBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListTerkiniViewModel
    private lateinit var adapter: ListTerkiniAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = ViewModelFactory.getInstance()
        viewModel =
            ViewModelProvider(requireActivity(), factory).get(ListTerkiniViewModel::class.java)

        _binding = FragmentListTerkiniBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTerkini.layoutManager = LinearLayoutManager(context)
        viewModel.getListDataGempaTerkini().observe(viewLifecycleOwner){data->
            if (data.isNotEmpty()){
                adapter = ListTerkiniAdapter()
                adapter.setDataGempaTerkini(data)
                binding.rvTerkini.adapter = adapter
            }
            Toast.makeText(requireContext(), "ini tampil", Toast.LENGTH_SHORT).show()
        }

    }

    private fun listDisplay(){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}