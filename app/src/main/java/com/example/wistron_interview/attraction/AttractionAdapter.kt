package com.example.wistron_interview.attraction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.databinding.ItemAttractionBinding

class AttractionAdapter(private val dataList: List<String>) :
    RecyclerView.Adapter<AttractionAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemAttractionBinding) : RecyclerView.ViewHolder(binding.root)

    private val onItemClickListener = View.OnClickListener { view: View? ->
        view?.let {
            val action = AttractionFragmentDirections.actionNavigationAttractionToNavigationDetail()
            Navigation.findNavController(it).navigate(action)
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
        binding.root.setOnClickListener(onItemClickListener)
    }

    override fun getItemCount(): Int {
        return 10
    }
}