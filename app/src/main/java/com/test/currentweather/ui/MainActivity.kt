package com.test.currentweather.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.currentweather.R
import com.test.currentweather.databinding.ActivityMainBinding
import com.test.currentweather.viewmodels.ForecastViewModel
import com.test.weathersdk.APIKeys.API_KEY
import com.test.weathersdk.APIKeys.TEMP_UNIT_IMPERIAL
import com.test.weathersdk.model.ForecastResponse


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var forecastViewModel: ForecastViewModel
    lateinit var forecast: ForecastResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)

        binding.etLocationName.setText("Vadodara")
        binding.mbSearch.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (!binding.etLocationName.text.isNullOrEmpty()){
                    getForecast(binding.etLocationName.text.toString())
                }
            }
        })

    }

    private fun getForecast(cityQuery: String) {
        forecastViewModel.getForecast(cityQuery, API_KEY, TEMP_UNIT_IMPERIAL)!!.observe(this, object : Observer<ForecastResponse?> {
            override fun onChanged(forecastResponse: ForecastResponse?) {
                if (forecastResponse != null) {
//                    forecast = forecastResponse
                    val sb = StringBuilder()
                    for (forecast in forecastResponse.list) {
                        for (weather in forecast.weather){
                            Log.e("DEBUG", "Weather At - "+forecast.dtTxt)
                            Log.e("DEBUG", "Current Weather "+weather.main + " "+weather.description)
                            sb.append("Weather - ${weather.main}  ${weather.description}")
                            break
                        }
                        Log.e("DEBUG", "Wind Speed  "+forecast.wind.speed+" meter/sec")
                        sb.append("\nWind Speed - ${forecast.wind.speed}  meter/sec")
                        Log.e("DEBUG", "Temp  "+forecast.main.temp)//F/C

                        sb.append("\nTemp - ${forecast.main.temp}  F")
                        break
                    }
                    binding.txtResult.setText(sb.toString())
                }
            }
        })
    }
}