package com.example.wistron_interview.attraction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wistron_interview.data.Place
import com.example.wistron_interview.databinding.ItemAttractionBinding

class AttractionAdapter(private val placeList: List<Place>) :
    RecyclerView.Adapter<AttractionAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemAttractionBinding) : RecyclerView.ViewHolder(binding.root)

    private val onItemClickListener = View.OnClickListener { view: View? ->
        view?.let {
            val position = it.tag as Int
//            val action = AttractionFragmentDirections.actionNavigationAttractionToNavigationDetail(placeList[position])
//            Navigation.findNavController(it).navigate(action)
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

        if(placeList[position].images.isNotEmpty()){
            Glide.with(binding.attractionImage)
                .load(placeList[position].images[1].src)
                .into(binding.attractionImage)
            binding.attractionPlaceHolder.visibility = View.GONE
        }else{
            binding.attractionPlaceHolder.visibility = View.VISIBLE
        }
        binding.titleTv.text = placeList[position].name
        binding.contentTv.text = placeList[position].introduction
        binding.root.setOnClickListener(onItemClickListener)
    }

    override fun getItemCount(): Int {
        return 10
    }
}