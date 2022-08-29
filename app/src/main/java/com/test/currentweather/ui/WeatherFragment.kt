package com.test.currentweather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.test.currentweather.Constants
import com.test.currentweather.R
import com.test.currentweather.databinding.FragmentWeatherBinding
import com.test.currentweather.viewmodels.ForecastViewModel
import com.test.currentweather.viewmodels.WeatherViewModel
import com.test.weathersdk.model.ForecastResponse
import com.test.weathersdk.model.WeatherResponse

class WeatherFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherFragment()
    }

    private lateinit var mBinding: FragmentWeatherBinding
    private lateinit var forecastViewModel: ForecastViewModel
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_weather, container, false)
        return mBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        getCurrentWeather("mumbai")
    }

    private fun getCurrentWeather(cityQuery: String) {
        weatherViewModel.getCurrentWeather(cityQuery, Constants.API_KEY,Constants.TEMP_UNIT_METRIC)!!
            .observe(viewLifecycleOwner, object : Observer<WeatherResponse?> {
            override fun onChanged(weatherResponse: WeatherResponse?) {
                if (weatherResponse != null) {
                    for (weather in weatherResponse.weather){
                        mBinding.txtWeatherType.setText(weather.main)
                        mBinding.txtWeatherDes.setText(weather.description)
                        Log.e("DEBUG","Weather - ${weather.main}  ${weather.description}")
                        break
                    }
                    mBinding.txtTempOfCity.setText("${weatherResponse.main.temp}°")
                    mBinding.txtTempOfCityDes.setText("feels Like ${weatherResponse.main.feelsLike}°")

                    Glide.with(mBinding.imgWeatherTypeIcon.getContext())
                        .load("https://openweathermap.org/img/wn/10d@2x.png")
                        .into(mBinding.imgWeatherTypeIcon)
                }
            }
        })
    }

    private fun getForecast(cityQuery: String) {
        forecastViewModel.getForecast(cityQuery, Constants.API_KEY, Constants.TEMP_UNIT_IMPERIAL)!!.observe(this, object :
            Observer<ForecastResponse?> {
            override fun onChanged(forecastResponse: ForecastResponse?) {
                if (forecastResponse != null) {
                    for (forecast in forecastResponse.list) {
                        for (weather in forecast.weather){
                            Log.e("DEBUG", "Weather At - "+forecast.dtTxt)
                            Log.e("DEBUG", "Current Weather "+weather.main + " "+weather.description)
                        }
                        Log.e("DEBUG", "Wind Speed  "+forecast.wind.speed+" meter/sec")
                        Log.e("DEBUG", "Temp  "+forecast.main.temp)//F/C
                    }
                }
            }
        })
    }

}