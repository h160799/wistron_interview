package com.example.wistron_interview.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wistron_interview.R
import com.example.wistron_interview.databinding.ItemDetailImageBinding
import com.github.chrisbanes.photoview.PhotoView
import com.wangpeiyuan.cycleviewpager2.adapter.CyclePagerAdapter

class DetailImageAdapter(
    private val list: List<String>?, private val context: Context
) : CyclePagerAdapter<DetailImageAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemDetailImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDetailImageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getRealItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindRealViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        Glide.with(binding.itemDetailImage)
            .load(list?.get(position))
            .placeholder(R.drawable.no_image)
            .error(R.drawable.error_image)
            .into(binding.itemDetailImage)

        binding.root.tag = position
    }
}