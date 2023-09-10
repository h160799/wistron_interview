package com.example.wistron_interview.ext

import androidx.fragment.app.Fragment
import com.example.wistron_interview.TaipeiTravelApplication
import com.example.wistron_interview.data.AttractionParameters
import com.example.wistron_interview.data.Place
import com.example.wistron_interview.factory.ViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as TaipeiTravelApplication).taipeiTravelRepository
    return ViewModelFactory(repository)
}

fun Fragment.getVmFactory(attractionParams: AttractionParameters): ViewModelFactory {
    val repository = (requireContext().applicationContext as TaipeiTravelApplication).taipeiTravelRepository
    return ViewModelFactory(repository, attractionParams)
}

