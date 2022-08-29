package com.test.weathersdk

class Constants {
    /*Temperature is available in Fahrenheit, Celsius and Kelvin units.

    For temperature in Fahrenheit use units=imperial
    For temperature in Celsius use units=metric
    Temperature in Kelvin is used by default, no need to use units parameter in API call*/

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

}