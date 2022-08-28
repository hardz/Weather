package com.test.weathersdk

import com.test.weathersdk.model.ForecastResponse
import com.test.weathersdk.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    //Current weather data
    //https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid={API key}
    //https://api.openweathermap.org/data/2.5/weather?q=mumbai&appid=7afbc4f5081a3bcbfa7dff0e32ce6b0d&units=imperial
    @GET("weather")
    fun getWeather(@Query("q") city :String, @Query("appid") appid :String, @Query("units") unit :String) : Call<WeatherResponse>


    //5 day weather forecast
    //https://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&appid={API key}
    //https://api.openweathermap.org/data/2.5/forecast?q=baroda&appid=ae1c4977a943a50eaa7da25e6258d8b2&units=imperial
    @GET("forecast")
    fun getForecast(@Query("q") city :String, @Query("appid") appid :String, @Query("units") unit :String) : Call<ForecastResponse>

}