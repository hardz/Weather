package com.test.currentweather

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun String.extractDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val localDateTime = LocalDateTime.parse(this, formatter)
    return localDateTime.toLocalDate().toString()
}

fun String.getWeatherIconURL(): String {
    return "https://openweathermap.org/img/wn/${this}@2x.png"
}


fun Double.round(): String {
    return kotlin.math.round(this).toString()
}