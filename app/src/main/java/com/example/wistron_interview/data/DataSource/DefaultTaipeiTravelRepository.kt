package com.example.wistron_interview.data.DataSource

import com.example.wistron_interview.data.Attraction
import com.example.wistron_interview.data.remote.TaipeiTravelRemoteDataSource
import com.example.wistron_interview.network.ApiResult

class DefaultTaipeiTravelRepository(
    private val taipeiTravelRemoteDataSource: TaipeiTravelRemoteDataSource,
) : TaipeiTravelRepository {
    override suspend fun getAttractList(
        lang: String,
        page: Int?,
        nLat: Double,
        eLong: Double
    ): ApiResult<Attraction> {
        return taipeiTravelRemoteDataSource.getAttractionList(
            lang = lang,
            page = page,
            nLat = nLat,
            eLong = eLong
        )
    }
}