package com.test.weathersdk.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WindX(
    @Json(name = "deg")
    val deg: Int,
    @Json(name = "speed")
    val speed: Double
)