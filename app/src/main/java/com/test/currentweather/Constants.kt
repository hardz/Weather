package com.test.currentweather

import com.test.currentweather.BuildConfig

class Constants {

    /*Temperature is available in Fahrenheit, Celsius and Kelvin units.

    For temperature in Fahrenheit use units=imperial
    For temperature in Celsius use units=metric
    Temperature in Kelvin is used by default, no need to use units parameter in API call*/

    companion object {
        const val API_KEY = BuildConfig.OPEN_WEATHER_API_KEY

        const val TEMP_UNIT_IMPERIAL = "imperial" //Fahrenheit
        const val TEMP_UNIT_METRIC = "metric" //Celsius
    }

}