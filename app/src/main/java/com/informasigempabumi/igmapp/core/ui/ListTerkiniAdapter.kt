package com.informasigempabumi.igmapp.core.ui

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.informasigempabumi.igmapp.R
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.utils.FormatTImeAgo
import com.informasigempabumi.igmapp.databinding.ItemTerkiniBinding
import com.informasigempabumi.igmapp.ui.detailGMP.DetailgmpActivity

class ListTerkiniAdapter : RecyclerView.Adapter<ListTerkiniAdapter.ViewHolder>() {

    private val listDataGempa = ArrayList<DataGempa>()
    fun setDataGempaTerkini(data: List<DataGempa>) {
        listDataGempa.clear()
        listDataGempa.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemTerkiniBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: DataGempa) {
            val magnitudo = data.magnitude!!.toDouble()
            binding.tvListWilayah.text = data.wilayah
            binding.tvListMagnitudo.text = data.magnitude
            binding.tvListTgl.text = data.tanggal
            binding.tvListJamYanglalu.text = data.dateTime?.let { FormatTImeAgo.getTimeAgo(it) }
            if (data.magnitude != null) {
                when (magnitudo) {
                    in 0.0..4.9 -> {
                        binding.materialCardView.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.bg_circle_green
                            )
                        )
                    }
                    in 5.0..6.0 -> {
                        binding.materialCardView.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.bg_circle_yellow
                            )
                        )
                    }
                    else -> {
                        binding.materialCardView.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.bg_circle_red
                            )
                        )
                    }
                }
            }
            itemView.setOnClickListener {
                val move = Intent(itemView.context, DetailgmpActivity::class.java)
                move.putExtra(DetailgmpActivity.EXTRA_DATA, data)
                itemView.context.startActivity(move)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTerkiniBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listDataGempa.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDataGempa[position])
    }
}