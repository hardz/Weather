package com.test.weathersdk

import com.test.weathersdk.APIKeys.API_KEY
import org.junit.Assert
import org.junit.Test

class WeatherAPITest {
    private val weatherService = WeatherService()
    @Test
    fun forecastTest(){
        val forecast = weatherService.weatherAPIRequest?.getForecast("mumbai", API_KEY)?.execute()
        Assert.assertNotNull(forecast!!.body())
    }

}