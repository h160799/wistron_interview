package com.example.wistron_interview.data

import com.example.wistron_interview.network.ApiResult

class DefaultTaipeiTravelRepository(
    private val taipeiTravelRemoteDataSource: TaipeiTravelDataSource,
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