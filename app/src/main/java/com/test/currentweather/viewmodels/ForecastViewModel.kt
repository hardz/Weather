package com.test.currentweather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.currentweather.model.ForecastResponse
import com.test.currentweather.repository.ForecastRepository

class ForecastViewModel : ViewModel(){
    var forecastResponseLiveData: MutableLiveData<ForecastResponse?>? = null

    fun getForecastbyGeoCode(lat :String, lon :String, appid :String, unit :String): LiveData<ForecastResponse?>? {
        if (forecastResponseLiveData == null) {
            forecastResponseLiveData = ForecastRepository().getForecastbyGeoCode(lat,lon, appid, unit)
        }
        return forecastResponseLiveData
    }
}