package com.test.currentweather.network

import com.test.currentweather.BuildConfig
import com.test.currentweather.model.ForecastResponse
import com.test.currentweather.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    //Current weather data
    //https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid={API key}&units=imperial
    @GET("weather?appid=${BuildConfig.OPEN_WEATHER_API_KEY}")
    fun getWeatherByGeoCode(@Query("lat") lat :String, @Query("lon") lon :String, @Query("units") unit :String) : Call<WeatherResponse>


    //5 day weather forecast
    //https://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&appid={API key}&units=imperial
    @GET("forecast?appid=${BuildConfig.OPEN_WEATHER_API_KEY}")
    fun getForecastByGeoCode(@Query("lat") lat :String, @Query("lon") lon :String, @Query("units") unit :String) : Call<ForecastResponse>

}