package com.example.wistron_interview.attraction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.wistron_interview.R
import com.example.wistron_interview.data.Place
import com.example.wistron_interview.databinding.ItemAttractionBinding

class AttractionAdapter(private val viewModel: AttractionViewModel) :
    ListAdapter<Place, AttractionAdapter.ViewHolder>(PlaceDiffCallback()) {

    private fun ImageView.loadImage(
        url: String?,
        placeholder: Int = R.drawable.no_image,
        error: Int = R.drawable.error_image
    ) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().placeholder(placeholder).error(error))
            .into(this)
    }

    class ViewHolder(val binding: ItemAttractionBinding) : RecyclerView.ViewHolder(binding.root)

    class PlaceDiffCallback : DiffUtil.ItemCallback<Place>() {

        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAttractionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding.root.tag = position

        val placeList = viewModel.attractionItems.value?.data ?: emptyList()
        val place = placeList[position]

        if (place.images.isNotEmpty()) {
            binding.attractionImage.loadImage(place.images[0].src)
        } else {
            binding.attractionImage.loadImage(null)
        }
        binding.titleTv.text = place.name
        binding.contentTv.text = place.introduction
        binding.root.setOnClickListener { viewModel.selectPlace(place) }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.binding.attractionImage.context).clear(holder.binding.attractionImage)
    }

    override fun getItemCount(): Int {
        return viewModel.attractionItems.value?.data?.size ?: 0
    }
}