package com.example.wistron_interview.attraction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wistron_interview.R
import com.example.wistron_interview.network.ApiResult
import com.example.wistron_interview.data.Attraction
import com.example.wistron_interview.data.AttractionParameters
import com.example.wistron_interview.data.Place
import com.example.wistron_interview.data.DataSource.TaipeiTravelRepository
import com.example.wistron_interview.data.LanguageInfo
import com.example.wistron_interview.data.LanguageType
import com.example.wistron_interview.network.LoadApiStatus
import com.example.wistron_interview.util.Logger
import com.example.wistron_interview.util.Util.getString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AttractionViewModel(
    private val taipeiTravelRepository: TaipeiTravelRepository,
    private val attractionParams: AttractionParameters
) : ViewModel() {
    private val _attractionItems = MutableLiveData<Attraction>()

    val attractionItems: LiveData<Attraction>
        get() = _attractionItems

    val selectedPlace: MutableLiveData<Place?> = MutableLiveData()

    private val _status = MutableLiveData<LoadApiStatus>()

    val status: LiveData<LoadApiStatus>
        get() = _status

    private val _error = MutableLiveData<String?>()

    val error: LiveData<String?>
        get() = _error

    var currentDistrict: String? = null

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")

        getAttractionResult(true)
    }

    fun selectPlace(place: Place?) {
        selectedPlace.value = place
    }
    fun getLanguageSubTitleFromParams(): String {
        val langFromParams = attractionParams.lang

        val index = LanguageInfo.values().indexOfFirst { it.lang == langFromParams }

        val languageType = LanguageType.fromIndex(index)

        return languageType.toLanguageSubTitle().title
    }

    fun loadMoreData() {
        attractionParams.page += 1

        getAttractionResult(false)
    }

    private fun getAttractionResult(isInitial: Boolean = false) {

        coroutineScope.launch {

            if (isInitial) _status.value = LoadApiStatus.LOADING

            val result = taipeiTravelRepository.getAttractList(
                attractionParams.lang,
                attractionParams.page,
                attractionParams.nLat.toDouble(),
                attractionParams.eLong.toDouble()
            )

            _attractionItems.value = when (result) {
                is ApiResult.Success -> {
                    _error.value = null
                    _status.value = LoadApiStatus.DONE

                    val currentData = _attractionItems.value?.data?.toMutableList() ?: mutableListOf()
                    currentData.addAll(result.data.data)

                    Attraction(
                        error = _attractionItems.value?.error,
                        total = _attractionItems.value?.total ?: (0 + result.data.total),
                        data = currentData
                    )
                }

                is ApiResult.Fail -> {
                    _error.value = result.error
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }

                is ApiResult.Error -> {
                    _error.value = result.exception.toString()
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }

                else -> {
                    _error.value = getString(R.string.error_check_internet)
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }

            }
        }
    }
}