package com.example.wistron_interview.network

import com.example.wistron_interview.BuildConfig
import com.example.wistron_interview.data.Attraction
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://www.travel.taipei/open-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = when (BuildConfig.LOGGER_VISIABLE) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
    })
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface ApiService {
    @Headers("Accept: application/json")
    @GET("{lang}/Attractions/All")
    suspend fun getAttractionList(@Path("lang") lang: String = "zh-tw", @Query("nlat") nLat: Double, @Query("elong") eLong: Double, @Query("page") page: Int? = null): Attraction
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object TaipeiTravelApi {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}