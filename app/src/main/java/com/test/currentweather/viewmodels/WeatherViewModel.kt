package com.test.currentweather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.currentweather.repository.WeatherRepository
import com.test.weathersdk.model.WeatherResponse

class WeatherViewModel : ViewModel(){
    var weatherResponseLiveData: MutableLiveData<WeatherResponse?>? = null
    fun getCurrentWeather(city :String, appid :String, unit :String): LiveData<WeatherResponse?>? {
        if (weatherResponseLiveData == null) {
            weatherResponseLiveData = WeatherRepository().getWeather(city, appid, unit)
        }
        return weatherResponseLiveData
    }

    fun getWeatherbyGeoCode(lat :String, lon: String, appid :String, unit :String): LiveData<WeatherResponse?>? {
        if (weatherResponseLiveData == null) {
            weatherResponseLiveData = WeatherRepository().getWeatherbyGeoCode(lat, lon, appid, unit)
        }
        return weatherResponseLiveData
    }
}