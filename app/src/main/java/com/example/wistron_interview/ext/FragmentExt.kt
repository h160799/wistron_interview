package com.example.wistron_interview.ext

import androidx.fragment.app.Fragment
import com.example.wistron_interview.TaipeiTravelApplication
import com.example.wistron_interview.data.Place
import com.example.wistron_interview.factory.ViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as TaipeiTravelApplication).taipeiTravelRepository
    return ViewModelFactory(repository)
}

fun Fragment.getVmFactory(lang: String, page: Int): ViewModelFactory {
    val repository = (requireContext().applicationContext as TaipeiTravelApplication).taipeiTravelRepository
    return ViewModelFactory(repository, lang = lang, page = page)
}

fun Fragment.getVmFactory(place: Place): ViewModelFactory {
    val repository = (requireContext().applicationContext as TaipeiTravelApplication).taipeiTravelRepository
    return ViewModelFactory(repository, place = place)
}