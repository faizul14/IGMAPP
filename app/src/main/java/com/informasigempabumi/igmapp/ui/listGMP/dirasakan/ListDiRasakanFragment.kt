package com.informasigempabumi.igmapp.ui.listGMP.dirasakan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.informasigempabumi.igmapp.core.ui.ListDirasakanAdapter
import com.informasigempabumi.igmapp.core.utils.ViewModelFactory
import com.informasigempabumi.igmapp.databinding.FragmentListDiRasakanBinding

class ListDiRasakanFragment : Fragment() {

    private var _binding: FragmentListDiRasakanBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListDirasakanViewModel
    private lateinit var adapter: ListDirasakanAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory = ViewModelFactory.getInstance()
        viewModel =
            ViewModelProvider(requireActivity(), factory).get(ListDirasakanViewModel::class.java)

        _binding = FragmentListDiRasakanBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDirasakan.layoutManager = LinearLayoutManager(context)
        viewModel.getListDataGempaDiRasakan().observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                adapter = ListDirasakanAdapter()
                adapter.setDataGempaTerkini(data)
                binding.rvDirasakan.adapter = adapter
                binding.shimerBaar.visibility = View.GONE
                binding.rvDirasakan.visibility = View.VISIBLE
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}