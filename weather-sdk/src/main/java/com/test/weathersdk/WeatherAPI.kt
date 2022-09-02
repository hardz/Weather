package com.test.weathersdk

import com.test.weathersdk.model.ForecastResponse
import com.test.weathersdk.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    //Current weather data

    //https://api.openweathermap.org/data/2.5/weather?q=mumbai&appid={API key}&units=imperial
    @GET("weather")
    fun getWeather(@Query("q") city :String, @Query("appid") appid :String, @Query("units") unit :String) : Call<WeatherResponse>

    //https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid={API key}&units=imperial
    @GET("weather")
    fun getWeatherbyGeoCode(@Query("lat") lat :String, @Query("lon") lon :String, @Query("appid") appid :String, @Query("units") unit :String) : Call<WeatherResponse>

    //5 day weather forecast

    //https://api.openweathermap.org/data/2.5/forecast?q=baroda&appid={API key}&units=imperial
    @GET("forecast")
    fun getForecast(@Query("q") city :String, @Query("appid") appid :String, @Query("units") unit :String) : Call<ForecastResponse>


    //https://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&appid={API key}&units=imperial
    @GET("forecast")
    fun getForecastbyGeoCode(@Query("lat") lat :String, @Query("lon") lon :String, @Query("appid") appid :String, @Query("units") unit :String) : Call<ForecastResponse>

}