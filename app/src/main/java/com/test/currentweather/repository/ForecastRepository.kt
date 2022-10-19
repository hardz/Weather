package com.test.currentweather.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.currentweather.model.ForecastResponse
import com.test.currentweather.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastRepository {
    private val TAG: String = ForecastRepository::class.java.getSimpleName()

    fun getForecastbyGeoCode(lat :String, lon :String, appid :String, unit :String): MutableLiveData<ForecastResponse?>? {
        val data: MutableLiveData<ForecastResponse?> = MutableLiveData<ForecastResponse?>()
        WeatherService().weatherAPIRequest?.getForecastbyGeoCode(lat, lon, appid, unit)?.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                if (response.body() != null) {
                    Log.e(TAG, "onResponse - "+response.body())
                    data.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.e(TAG, "onFailure - ${t.message}")
                data.setValue(null)
            }

        })
        return data
    }
}