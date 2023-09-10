package com.example.wistron_interview.data

import com.squareup.moshi.Json

data class Place(
    val id: Int,
    val name: String,
    @Json(name = "name_zh")
    val nameZh: String? = null,
    @Json(name = "open_status")
    val openStatus: Int,
    val introduction: String,
    @Json(name = "open_time")
    val openTime: String,
    val zipcode: String,
    val distric: String,
    val address: String,
    val tel: String,
    val fax: String,
    val email: String,
    val months: String,
    @Json(name = "nlat")
    val nLat: Double,   //北緯
    @Json(name = "elong")
    val eLong: Double,   //東經
    @Json(name = "official_site")
    val officialSite: String,
    val facebook: String,
    val ticket: String,
    val remind: String,
    @Json(name = "staytime")
    val stayTime: String,
    val modified: String,
    val url: String,
    val category: List<Category>,
    val target: List<Target>,
    val service: List<Service>,
    val friendly: List<Friendly>,
    val images: List<Image>,
    val files: List<File>,
    val links: List<Link>
)

