package com.example.wistron_interview.data

import com.example.wistron_interview.network.ApiResult

interface TaipeiTravelRepository {
    suspend fun getAttractList(lang: String, page: Int?, nLat: Double, eLong: Double): ApiResult<Attraction>
}