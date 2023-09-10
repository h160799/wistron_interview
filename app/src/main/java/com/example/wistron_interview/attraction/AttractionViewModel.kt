package com.example.wistron_interview.attraction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wistron_interview.R
import com.example.wistron_interview.network.ApiResult
import com.example.wistron_interview.data.Attraction
import com.example.wistron_interview.data.Place
import com.example.wistron_interview.data.DataSource.TaipeiTravelRepository
import com.example.wistron_interview.network.LoadApiStatus
import com.example.wistron_interview.util.Logger
import com.example.wistron_interview.util.Util.getString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AttractionViewModel(
    private val taipeiTravelRepository: TaipeiTravelRepository,
    private val lang: String,
    private val page: Int,
    private val nLat: String,
    private val eLong: String
) : ViewModel() {
    private val _attractionItems = MutableLiveData<Attraction>()

    val attractionItems: LiveData<Attraction>
        get() = _attractionItems

    private val _status = MutableLiveData<LoadApiStatus>()

    val status: LiveData<LoadApiStatus>
        get() = _status

    private val _error = MutableLiveData<String?>()

    val error: LiveData<String?>
        get() = _error

    private val _navigateToDetail = MutableLiveData<Place?>()

    val navigateToDetail: LiveData<Place?>
        get() = _navigateToDetail

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

    private fun getAttractionResult(isInitial: Boolean = false) {

        coroutineScope.launch {

            if (isInitial) _status.value = LoadApiStatus.LOADING

            Logger.e("fffffffff${lang}${page}${nLat},${eLong}")

            val result = taipeiTravelRepository.getAttractList(lang, page, nLat.toDouble(), eLong.toDouble())

            _attractionItems.value = when (result) {
                is ApiResult.Success -> {
                    _error.value = null
                    if (isInitial) _status.value = LoadApiStatus.DONE
                    result.data

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

    fun navigateToDetail(place: Place) {
        _navigateToDetail.value = place
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }
}