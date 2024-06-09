package com.test.currentweather.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.currentweather.model.WeatherResponse
import com.test.currentweather.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {
    private val TAG: String = WeatherRepository::class.java.getSimpleName()

    fun getWeatherByGeoCode(lat :String, lon :String, unit :String): MutableLiveData<WeatherResponse?> {
        val data: MutableLiveData<WeatherResponse?> = MutableLiveData<WeatherResponse?>()
        WeatherService().weatherAPIRequest?.getWeatherByGeoCode(lat, lon, unit)?.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.body() != null) {
                    Log.e(TAG, "onResponse  - "+response.body())
                    data.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(TAG, "onFailure  - ${t.message}")
                data.setValue(null)
            }

        })
        return data
    }


}