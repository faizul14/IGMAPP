package com.informasigempabumi.igmapp.ui.listGMP.dirasakan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.informasigempabumi.igmapp.core.ui.ListDirasakanAdapter
import com.informasigempabumi.igmapp.databinding.FragmentListDiRasakanBinding
import org.koin.android.viewmodel.ext.android.viewModel


class ListDiRasakanFragment : Fragment() {

    private var _binding: FragmentListDiRasakanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListDirasakanAdapter
    private val viewModel: ListDirasakanViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
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