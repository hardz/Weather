package com.test.weathersdk

import com.test.weathersdk.APIKeys.API_KEY
import org.junit.Assert
import org.junit.Test

class WeatherAPITest {

    private val weatherService = WeatherService()
    @Test
    fun forcastTest(){
        val forcast = weatherService.api.getForecast("mumbai", API_KEY).execute()
        Assert.assertNotNull(forcast.body())
    }

}