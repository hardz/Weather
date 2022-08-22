package com.test.currentweather.repository

import androidx.lifecycle.MutableLiveData
import com.test.weathersdk.WeatherService
import com.test.weathersdk.model.ForecastResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastRepository {
    private val TAG: String = ForecastRepository::class.java.getSimpleName()

    fun getForecast(city :String, appid :String, unit :String): MutableLiveData<ForecastResponse?>? {
        val data: MutableLiveData<ForecastResponse?> = MutableLiveData<ForecastResponse?>()
        WeatherService().weatherAPIRequest?.getForecast(city, appid, unit)?.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                if (response.body() != null) {
                    data.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                data.setValue(null)
            }

        })
        return data
    }
}