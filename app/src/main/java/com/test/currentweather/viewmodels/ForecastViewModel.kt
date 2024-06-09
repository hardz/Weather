package com.test.currentweather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.currentweather.model.ForecastResponse
import com.test.currentweather.repository.ForecastRepository

class ForecastViewModel : ViewModel(){
    private var forecastResponseLiveData: MutableLiveData<ForecastResponse?>? = null

    fun getForecastByGeoCode(lat :String, lon :String, unit :String): LiveData<ForecastResponse?>? {
        if (forecastResponseLiveData == null) {
            forecastResponseLiveData = ForecastRepository().getForecastByGeoCode(lat,lon, unit)
        }
        return forecastResponseLiveData
    }
}