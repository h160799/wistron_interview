package com.example.wistron_interview.detail

import androidx.lifecycle.ViewModel
import com.example.wistron_interview.data.Place
import com.example.wistron_interview.data.TaipeiTravelRepository

class DetailViewModel(
    private val taipeiTravelRepository: TaipeiTravelRepository,
    private val place: Place
) : ViewModel() {
}