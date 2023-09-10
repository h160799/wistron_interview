package com.example.wistron_interview.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wistron_interview.attraction.AttractionViewModel
import com.example.wistron_interview.data.AttractionParameters
import com.example.wistron_interview.data.DataSource.TaipeiTravelRepository
import com.example.wistron_interview.detail.DetailViewModel
import com.example.wistron_interview.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val taipeiTravelRepository: TaipeiTravelRepository,
    private val attractionParams: AttractionParameters? = null

) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(taipeiTravelRepository)
                isAssignableFrom(AttractionViewModel::class.java) -> {
                    requireNotNull(attractionParams)
                    AttractionViewModel(taipeiTravelRepository, attractionParams)
                }
                isAssignableFrom(DetailViewModel::class.java) -> {
                    DetailViewModel(taipeiTravelRepository)
                }
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}