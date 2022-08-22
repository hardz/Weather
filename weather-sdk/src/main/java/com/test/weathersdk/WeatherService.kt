package com.test.weathersdk

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.weathersdk.APIKeys.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherService {

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


    val weatherAPIRequest: WeatherAPI? = retrofit.create(WeatherAPI::class.java)
}