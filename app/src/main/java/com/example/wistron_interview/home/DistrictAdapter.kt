package com.example.wistron_interview.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wistron_interview.data.AttractionParameters
import com.example.wistron_interview.data.DistrictInfo
import com.example.wistron_interview.data.DistrictNameCN
import com.example.wistron_interview.data.DistrictNameEN
import com.example.wistron_interview.data.DistrictNameES
import com.example.wistron_interview.data.DistrictNameJA
import com.example.wistron_interview.data.DistrictNameKO
import com.example.wistron_interview.data.DistrictNameTH
import com.example.wistron_interview.data.DistrictNameVI
import com.example.wistron_interview.data.DistrictNameZH
import com.example.wistron_interview.data.LanguageInfo
import com.example.wistron_interview.data.LanguageType
import com.example.wistron_interview.databinding.ItemDistrictBinding

class DistrictAdapter(checkedItem: Int, private val itemHeights: MutableList<Int>) :
    RecyclerView.Adapter<DistrictAdapter.ViewHolder>() {

    private val languageType = LanguageType.fromIndex(checkedItem)

    private val districtNames = when (languageType) {
        LanguageType.ZH -> DistrictNameZH.values().map { it.districtName }
        LanguageType.CN -> DistrictNameCN.values().map { it.districtName }
        LanguageType.EN -> DistrictNameEN.values().map { it.districtName }
        LanguageType.JA -> DistrictNameJA.values().map { it.districtName }
        LanguageType.KO -> DistrictNameKO.values().map { it.districtName }
        LanguageType.ES -> DistrictNameES.values().map { it.districtName }
        LanguageType.TH -> DistrictNameTH.values().map { it.districtName }
        LanguageType.VI -> DistrictNameVI.values().map { it.districtName }
    }

    class ViewHolder(val binding: ItemDistrictBinding) : RecyclerView.ViewHolder(binding.root)

    private val onItemClickListener = View.OnClickListener { view: View? ->
        view?.let {
            val position = it.tag as Int
            if (position in 1 until DistrictInfo.values().size + 1) {
                val district = DistrictInfo.values()[position - 1]
                val languageInfo = LanguageInfo.values()[checkedItem]
                val parameters = AttractionParameters(
                    languageInfo.lang,
                    nLat = district.nLat,
                    eLong = district.eLong
                )
                val action = HomeFragmentDirections.actionNavigationHomeToNavigationAttraction(
                    parameters
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
        if (itemHeights[position] == 0) {
            val heights = listOf(400, 500, 600)
            itemHeights[position] = heights.random()
        }
        layoutParams.height = itemHeights[position]

        binding.root.tag = position
        binding.districtText.text = districtNames[position]
        binding.root.setOnClickListener(onItemClickListener)

        if (position in 1 until DistrictInfo.values().size + 1) {
            val selectedDistrict = DistrictInfo.values()[position-1]
            binding.districtImage.background = ContextCompat.getDrawable(
                binding.districtImage.context, selectedDistrict.Image
            )
        }
    }

    override fun getItemCount(): Int {
        return districtNames.size
    }
}