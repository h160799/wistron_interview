package com.example.wistron_interview.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.wistron_interview.BaseFragment
import com.example.wistron_interview.R
import com.example.wistron_interview.databinding.FragmentDetailBinding
import com.github.chrisbanes.photoview.PhotoView
import com.wangpeiyuan.cycleviewpager2.CycleViewPager2Helper
import com.wangpeiyuan.cycleviewpager2.indicator.DotsIndicator

class DetailFragment : BaseFragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = listOf("https://media.istockphoto.com/id/506472311/zh/%E7%85%A7%E7%89%87/sunrise.jpg?s=612x612&w=0&k=20&c=sbcVyr8IUl10L4h2Sx3252PRkcMUcy6yXZnJOsnPPBQ=",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqgLIVraWaIHoJangei04bHHhTxpHDTxKX9w&usqp=CAU",
            "https://taroko.lakeshore.com.tw/wp-content/uploads/sites/145/2021/01/TK-Public-area-%E9%A2%A8%E6%99%AF%E7%85%A7-4.png")


        //照片輪播
        CycleViewPager2Helper(binding.imageAdPager)
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


    }

    override fun onResume() {
        super.onResume()
        showLoader(View.GONE)
    }
}