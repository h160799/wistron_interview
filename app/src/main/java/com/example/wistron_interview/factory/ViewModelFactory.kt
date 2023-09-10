package com.example.wistron_interview.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wistron_interview.attraction.AttractionViewModel
import com.example.wistron_interview.data.Place
import com.example.wistron_interview.data.TaipeiTravelRepository
import com.example.wistron_interview.detail.DetailViewModel
import com.example.wistron_interview.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val taipeiTravelRepository: TaipeiTravelRepository,
    private val place: Place? = null,
    private val lang: String? = null,
    private val page: Int? = null
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(taipeiTravelRepository)
                isAssignableFrom(AttractionViewModel::class.java) -> {
                    requireNotNull(lang)
                    requireNotNull(page)
                    AttractionViewModel(taipeiTravelRepository, lang, page)
                }
                isAssignableFrom(DetailViewModel::class.java) -> {
                    requireNotNull(place)
                    DetailViewModel(taipeiTravelRepository, place)
                }
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}