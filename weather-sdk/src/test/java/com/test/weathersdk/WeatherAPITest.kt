package com.test.weathersdk

import com.test.weathersdk.Constants.Companion.API_KEY
import com.test.weathersdk.Constants.Companion.TEMP_UNIT_IMPERIAL
import com.test.weathersdk.Constants.Companion.TEMP_UNIT_METRIC
import org.junit.Assert
import org.junit.Test

class WeatherAPITest {
    private val weatherService = WeatherService()
    @Test
    fun forecastTest(){
        val forecast = weatherService.weatherAPIRequest?.getForecast("mumbai", API_KEY, TEMP_UNIT_IMPERIAL)?.execute()
        Assert.assertNotNull(forecast!!.body())
    }

    @Test
    fun weatherTest(){
        val weather = weatherService.weatherAPIRequest?.getWeather("new york", API_KEY, TEMP_UNIT_METRIC)?.execute()
        Assert.assertNotNull(weather!!.body())
    }
}