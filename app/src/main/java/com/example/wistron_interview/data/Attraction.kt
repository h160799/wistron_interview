package com.example.wistron_interview.data

data class Attraction(
    val error: String? = null,
    val total: Int,
    val data: List<Place>
)
