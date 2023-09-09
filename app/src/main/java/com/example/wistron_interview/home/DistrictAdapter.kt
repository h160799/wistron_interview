package com.example.wistron_interview.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wistron_interview.databinding.ItemDistrictBinding

class DistrictAdapter(private val dataList: List<String>) :
    RecyclerView.Adapter<DistrictAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemDistrictBinding) : RecyclerView.ViewHolder(binding.root)

    private val onItemClickListener = View.OnClickListener { view: View? ->
            view?.let {
                val action = HomeFragmentDirections.actionNavigationHomeToNavigationAttraction()
                Navigation.findNavController(it).navigate(action)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDistrictBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val layoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val heights = listOf(400,500,600)
        val randomHeight = heights.random()
        layoutParams.height = randomHeight

        binding.districtText.text = "大安區"
        binding.root.tag = position
        binding.root.setOnClickListener(onItemClickListener)
    }

    override fun getItemCount(): Int {
        return 13
    }
}