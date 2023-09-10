package com.example.wistron_interview.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wistron_interview.TaipeiTravelApplication
import com.example.wistron_interview.data.DataSource.TaipeiTravelRepository
import com.example.wistron_interview.data.LanguageTitle

class HomeViewModel(private val taipeiTravelRepository: TaipeiTravelRepository) : ViewModel() {

    private val _basePosition = MutableLiveData<Int>()
    val basePosition: LiveData<Int>
        get() = _basePosition

    private val _checkItem = MutableLiveData<Int>()
    val checkItem: LiveData<Int>
        get() = _checkItem

    private val _languageTitle = MutableLiveData<String>()
    val languageTitle: LiveData<String>
        get() = _languageTitle

    val showLanguageDialog = MutableLiveData<Boolean>(false)

    init {
        val sharedPref =
            TaipeiTravelApplication.instance.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        _basePosition.value = sharedPref?.getInt(KEY_BASE_POSITION, 0) ?: 0
    }

    fun changeLanguage() {
        showLanguageDialog.value = true
    }

    fun saveLanguagePreference(index: Int) {
        _checkItem.value = index
        _languageTitle.value = LanguageTitle.fromIndex(index)

        val sharedPref =
            TaipeiTravelApplication.instance.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(KEY_BASE_POSITION, index)
            commit()
        }

        _basePosition.value = index // 更新basePosition的值
    }

    fun updateCheckItemValue(itemPosition: Int) {
        _checkItem.value = itemPosition
        _languageTitle.value = LanguageTitle.fromIndex(itemPosition)
    }

    companion object {
        private const val PREF_NAME = "Select_Language"
        private const val KEY_BASE_POSITION = "basePosition"
    }
}