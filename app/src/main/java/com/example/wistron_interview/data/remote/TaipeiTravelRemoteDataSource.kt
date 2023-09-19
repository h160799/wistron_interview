package com.example.wistron_interview.data.remote

import com.example.wistron_interview.R
import com.example.wistron_interview.data.Attraction
import com.example.wistron_interview.data.dataSource.TaipeiTravelDataSource
import com.example.wistron_interview.network.ApiResult
import com.example.wistron_interview.network.TaipeiTravelApi
import com.example.wistron_interview.util.Logger
import com.example.wistron_interview.util.Util.getString
import com.example.wistron_interview.util.Util.isInternetConnected

object TaipeiTravelRemoteDataSource: TaipeiTravelDataSource {
    override suspend fun getAttractionList(lang: String, page: Int?, nLat: Double, eLong: Double): ApiResult<Attraction> {
        if (!isInternetConnected()) {
            return ApiResult.Fail(getString(R.string.no_internet))
        }

        return try {
            // this will run on a thread managed by Retrofit
            val result = TaipeiTravelApi.retrofitService.getAttractionList(lang =lang, page =page, nLat = nLat, eLong = eLong)

            result.error?.let {
                return ApiResult.Fail(it)
            }
            ApiResult.Success(result)

        } catch (e: Exception) {
            Logger.w("[${this::class.simpleName}] exception=${e.message}")
            ApiResult.Error(e)
        }
    }
}