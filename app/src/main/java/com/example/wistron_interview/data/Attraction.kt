package com.example.wistron_interview.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class Attraction(
    val error: String? = null,
    val total: Int,
    val data: List<Place>
)
@Parcelize
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
    val nLat: Double,
    @Json(name = "elong")
    val eLong: Double,
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
): Parcelable
@Parcelize
data class Category(
    val id: Int,
    val name: String
): Parcelable
@Parcelize
data class Target(
    val id: Int,
    val name: String
): Parcelable
@Parcelize
data class Service(
    val id: Int,
    val name: String
): Parcelable
@Parcelize
data class Friendly(
    val id: Int,
    val name: String
): Parcelable
@Parcelize
data class Image(
    val src: String,
    val subject: String,
    val ext: String
): Parcelable
@Parcelize
data class File(
    val file: List<String>? = null,
): Parcelable
@Parcelize
data class Link(
    val src: String,
    val subject: String
): Parcelable
@Parcelize
data class AttractionParameters(
    val lang: String,
    var page: Int=1,
    val nLat: String,
    val eLong: String
): Parcelable