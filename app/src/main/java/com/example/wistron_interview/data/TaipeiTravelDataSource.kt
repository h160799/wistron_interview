package com.example.wistron_interview.data

import com.example.wistron_interview.network.ApiResult

interface TaipeiTravelDataSource {
    suspend fun getAttractionList(
        lang: String,
        page: Int?,
        nLat: Double,
        eLong: Double
    ): ApiResult<Attraction>
}