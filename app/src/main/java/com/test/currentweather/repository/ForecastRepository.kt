package com.test.currentweather.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.currentweather.model.ForecastResponse
import com.test.currentweather.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastRepository {
    private val TAG: String = ForecastRepository::class.java.simpleName

    fun getForecastByGeoCode(lat :String, lon :String, unit :String): MutableLiveData<ForecastResponse?>? {
        val data: MutableLiveData<ForecastResponse?> = MutableLiveData<ForecastResponse?>()
        WeatherService().weatherAPIRequest?.getForecastByGeoCode(lat, lon, unit)?.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                if (response.body() != null) {
                    Log.e(TAG, "onResponse - "+response.body())
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.e(TAG, "onFailure - ${t.message}")
                data.value = null
            }

        })
        return data
    }
}