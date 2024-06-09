package com.test.currentweather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.currentweather.model.WeatherResponse
import com.test.currentweather.repository.WeatherRepository

class WeatherViewModel : ViewModel(){
    private var weatherResponseLiveData: MutableLiveData<WeatherResponse?>? = null

    fun getWeatherByGeoCode(lat :String, lon: String, unit :String): LiveData<WeatherResponse?>? {
        if (weatherResponseLiveData == null) {
            weatherResponseLiveData = WeatherRepository().getWeatherByGeoCode(lat, lon, unit)
        }
        return weatherResponseLiveData
    }
}