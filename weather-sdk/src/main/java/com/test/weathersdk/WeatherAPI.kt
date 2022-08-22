package com.test.weathersdk

import com.test.weathersdk.model.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("forecast")
    fun getForecast(@Query("q") city :String, @Query("appid") appid :String) : Call<ForecastResponse>
}