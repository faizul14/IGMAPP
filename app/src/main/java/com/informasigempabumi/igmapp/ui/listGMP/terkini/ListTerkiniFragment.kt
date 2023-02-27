package com.informasigempabumi.igmapp.ui.listGMP.terkini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.informasigempabumi.igmapp.core.ui.ListTerkiniAdapter
import com.informasigempabumi.igmapp.databinding.FragmentListTerkiniBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ListTerkiniFragment : Fragment() {

    private var _binding: FragmentListTerkiniBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListTerkiniAdapter
    private val viewModel: ListTerkiniViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListTerkiniBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTerkini.layoutManager = LinearLayoutManager(context)
        viewModel.getListDataGempaTerkini().observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                adapter = ListTerkiniAdapter()
                adapter.setDataGempaTerkini(data)
                binding.rvTerkini.adapter = adapter
                binding.shimerBaar.visibility = View.GONE
                binding.rvTerkini.visibility = View.VISIBLE
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}