package com.example.wistron_interview.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wistron_interview.BaseFragment
import com.example.wistron_interview.R
import com.example.wistron_interview.data.Place
import com.example.wistron_interview.databinding.FragmentDetailBinding
import com.example.wistron_interview.ext.getVmFactory
import com.example.wistron_interview.util.Logger
import com.wangpeiyuan.cycleviewpager2.CycleViewPager2Helper
import com.wangpeiyuan.cycleviewpager2.indicator.DotsIndicator

class DetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var place : Place

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.apply {
            val args = DetailFragmentArgs.fromBundle(this)
            place = args.place
        }

        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val defaultImageList = listOf("")
        val imageList = place.images.map { it.src }.ifEmpty { defaultImageList }

        if (imageList.isNotEmpty()) {
            CycleViewPager2Helper(binding.imagePager)
                .setAdapter(context?.let { DetailImageAdapter(imageList, it) })
                .setDotsIndicator(
                    20f,
                    ContextCompat.getColor(requireActivity(), R.color.grey),
                    ContextCompat.getColor(requireActivity(), R.color.white),
                    20f,
                    0,
                    resources.getDimension(R.dimen.indicator_bottom_margin).toInt(),
                    0,
                    DotsIndicator.Direction.CENTER
                )
                .setAutoTurning(3000L)
                .build()
        }else{
            binding.detailImage.setBackgroundResource(R.drawable.error_image)
        }

        binding.titleTv.text = place.name
        binding.contentTv.text = place.introduction
        binding.contentUrl.text = place.url
        binding.contentUrl.setOnClickListener {
            val action = DetailFragmentDirections.actionNavigationDetailToNavigationWebView(place.url)
            Navigation.findNavController(it).navigate(action)
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}