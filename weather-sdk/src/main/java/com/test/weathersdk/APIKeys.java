package com.test.weathersdk;

public class APIKeys {

    public static String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static String API_KEY = "ae1c4977a943a50eaa7da25e6258d8b2";

    /*Temperature is available in Fahrenheit, Celsius and Kelvin units.

    For temperature in Fahrenheit use units=imperial
    For temperature in Celsius use units=metric
    Temperature in Kelvin is used by default, no need to use units parameter in API call*/

    public static String TEMP_UNIT_IMPERIAL = "imperial"; //Fahrenheit
    public static String TEMP_UNIT_METRIC = "metric"; //Celsius

}
