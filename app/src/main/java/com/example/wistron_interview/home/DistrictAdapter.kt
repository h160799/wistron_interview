package com.example.wistron_interview.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wistron_interview.data.DistrictInfo
import com.example.wistron_interview.data.LanguageInfo
import com.example.wistron_interview.databinding.ItemDistrictBinding

class DistrictAdapter(private val districtList: List<String>, checkedItem: Int) :
    RecyclerView.Adapter<DistrictAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemDistrictBinding) : RecyclerView.ViewHolder(binding.root)

    private val onItemClickListener = View.OnClickListener { view: View? ->
            view?.let {
                val position = it.tag as Int
                if (position in 1 until DistrictInfo.values().size) {
                    val district = DistrictInfo.values()[position - 1] // 由於列表索引從0開始，需要減1
                    val languageInfo = LanguageInfo.values()[checkedItem]
                    val action = HomeFragmentDirections.actionNavigationHomeToNavigationAttraction(
                        languageInfo.lang, district.nLat, district.eLong
                    )
                    Navigation.findNavController(it).navigate(action)
                }
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

        binding.root.tag = position
        binding.districtText.text = districtList[position]
        binding.root.setOnClickListener(onItemClickListener)
    }

    override fun getItemCount(): Int {
        return districtList.size
    }
}