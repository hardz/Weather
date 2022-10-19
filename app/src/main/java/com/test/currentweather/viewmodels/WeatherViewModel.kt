package com.test.currentweather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.currentweather.model.WeatherResponse
import com.test.currentweather.repository.WeatherRepository

class WeatherViewModel : ViewModel(){
    var weatherResponseLiveData: MutableLiveData<WeatherResponse?>? = null

    fun getWeatherbyGeoCode(lat :String, lon: String, appid :String, unit :String): LiveData<WeatherResponse?>? {
        if (weatherResponseLiveData == null) {
            weatherResponseLiveData = WeatherRepository().getWeatherbyGeoCode(lat, lon, appid, unit)
        }
        return weatherResponseLiveData
    }
}