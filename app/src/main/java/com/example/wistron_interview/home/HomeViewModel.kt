package com.example.wistron_interview.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wistron_interview.data.DataSource.TaipeiTravelRepository

class HomeViewModel(private val taipeiTravelRepository: TaipeiTravelRepository) : ViewModel() {

    private val _checkItem = MutableLiveData<Int>()
    val checkItem: LiveData<Int>
        get() = _checkItem
    fun updateCheckItemValue(itemPosition: Int) {
        _checkItem.value = itemPosition
    }

}