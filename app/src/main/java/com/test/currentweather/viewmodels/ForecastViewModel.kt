package com.test.currentweather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.currentweather.repository.ForecastRepository
import com.test.weathersdk.model.ForecastResponse

class ForecastViewModel : ViewModel(){
    var forecastResponseLiveData: MutableLiveData<ForecastResponse?>? = null
    fun getForecast(city :String, appid :String, unit :String): LiveData<ForecastResponse?>? {
        if (forecastResponseLiveData == null) {
            forecastResponseLiveData = ForecastRepository().getForecast(city, appid, unit)
        }
        return forecastResponseLiveData
    }
}