package com.test.currentweather.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.weathersdk.WeatherService
import com.test.weathersdk.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {
    private val TAG: String = WeatherRepository::class.java.getSimpleName()

    fun getWeather(city :String, appid :String, unit :String): MutableLiveData<WeatherResponse?>? {
        val data: MutableLiveData<WeatherResponse?> = MutableLiveData<WeatherResponse?>()
        WeatherService().weatherAPIRequest?.getWeather(city, appid, unit)?.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.body() != null) {
                    Log.e("DEBUG", "onResponse  "+response.body())
                    data.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("DEBUG", "onFailure  "+t)
                data.setValue(null)
            }

        })
        return data
    }
}