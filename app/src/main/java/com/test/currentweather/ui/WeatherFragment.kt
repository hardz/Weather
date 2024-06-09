package com.test.currentweather.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.test.currentweather.Constants
import com.test.currentweather.R
import com.test.currentweather.databinding.FragmentWeatherBinding
import com.test.currentweather.model.Forecast
import com.test.currentweather.viewmodels.ForecastViewModel
import com.test.currentweather.viewmodels.WeatherViewModel

class WeatherFragment : Fragment(), DaysForecastAdapter.ForecastAdapterListener {

    private lateinit var daysForecastAdapter: DaysForecastAdapter
    private lateinit var rvDayForecast: RecyclerView
    private lateinit var mBinding: FragmentWeatherBinding
    private lateinit var forecastViewModel: ForecastViewModel
    private lateinit var weatherViewModel: WeatherViewModel

    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var PERMISSION_ID = 44

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        return mBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(view.context)

        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        setupForecastList()

        mBinding.header.openSetting.setOnClickListener {
            Navigation.findNavController(mBinding.root).navigate(WeatherFragmentDirections.actionWeatherFragmentToSettingsFragment())
        }
    }

    private fun setupForecastList() {
        rvDayForecast = mBinding.dayForecast
        val verticalPadding = resources.getDimensionPixelSize(R.dimen.album_list_padding_vertical)
        rvDayForecast.setPadding(0, verticalPadding, 0, verticalPadding)
        rvDayForecast.setClipToPadding(false)
        rvDayForecast.setLayoutManager(LinearLayoutManager(context))

        daysForecastAdapter = DaysForecastAdapter(this)
        rvDayForecast.setAdapter(daysForecastAdapter)
    }

    override fun onResume() {
        super.onResume()
        getLastLocation()
    }

    @SuppressLint("MissingPermission")
    fun getLastLocation() {
        if (!checkPermissions()) {
            // if permissions aren't available, request for permissions
            requestPermissions()
        } else {
            // check if location is enabled
            if (isLocationEnabled()) {
                mFusedLocationClient!!.lastLocation.addOnCompleteListener { task ->
                    val location = task.result
                    if (location != null) {
                        Log.e("DEBUG","Lat  --- " + location.latitude + "    Long  --- " + location.longitude)
                        getForecastByGeoCode(location.latitude, location.longitude)
                        getCurrentWeatherByGeoCode(location.latitude, location.longitude)
                    }
                }
            } else {
                Toast.makeText(requireActivity(),"Please turn on your location...",Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager =
            activity?.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    // method to check for permissions
    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
            ), PERMISSION_ID
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_ID -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun getCurrentWeatherByGeoCode(latitude: Double, longitude: Double) {
        weatherViewModel.getWeatherByGeoCode(
            latitude.toString(),
            longitude.toString(),
            Constants.TEMP_UNIT_METRIC
        )!!.observe(viewLifecycleOwner) { weatherResponse ->
            if (weatherResponse != null) {
                for (weather in weatherResponse.weather) {
                    mBinding.weatherMainDetail.weatherType.text = weather.main
                    mBinding.weatherMainDetail.weatherDescription.text = weather.description
                    Glide.with(mBinding.weatherMainDetail.weatherTypeIcon.context)
                        .load(getWeatherIconURL(weather.icon))
                        .into(mBinding.weatherMainDetail.weatherTypeIcon)
                    break
                }
                mBinding.header.cityName.text = weatherResponse.name

                mBinding.weatherMainDetail.tempMaxMin.text = getString(
                    R.string.temp_high_low,
                    weatherResponse.main.tempMax.round(),
                    weatherResponse.main.tempMin.round()
                )
                //Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
                val temp = weatherResponse.main.temp.round() + "°"
                val feelsLike = "${weatherResponse.main.feelsLike}".substringBefore(".") + "°"

                mBinding.weatherMainDetail.cityTemp.text = temp
                mBinding.weatherMainDetail.cityTempDescription.text = "feels Like $feelsLike"

                //Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
                mBinding.windHumidityDetail.txtWindSpeed.text = "${weatherResponse.wind.speed} miles/hour"
                mBinding.windHumidityDetail.txtHumidity.text = "${weatherResponse.main.humidity}%"

                //mBinding.txtSunrise.setText("${getFormattedDate(weatherResponse.sys.sunrise)}")
                //mBinding.txtSunset.setText("${getFormattedDate(weatherResponse.sys.sunset)}")
            }
        }
    }

    private fun Double.round(): String {
        return kotlin.math.round(this).toString()
    }

    private fun getWeatherIconURL(icon: String): String {
        return "https://openweathermap.org/img/wn/${icon}@2x.png"
    }

    private fun getForecastByGeoCode(latitude: Double, longitude: Double) {
        forecastViewModel.getForecastByGeoCode(
            latitude.toString(),
            longitude.toString(),
            Constants.TEMP_UNIT_METRIC
        )!!.observe(viewLifecycleOwner) { forecastResponse ->
            if (forecastResponse != null) {
                Log.e("DEBUG", "getForecastbyGeoCode  --- " + forecastResponse)
                val forecastlist: ArrayList<Forecast> = arrayListOf()
                var repeatedDate = "0"
                for (forecast in forecastResponse.list) {
                    if (!repeatedDate.equals(forecast.dtTxt.split(" ")[0])) {
                        Log.e(
                            "DEBUG",
                            "getForecastbyGeoCode  --- " + forecast.weather[0].description
                        )
                        forecastlist.add(forecast)
                        repeatedDate = forecast.dtTxt.split(" ")[0]
                    }
                }
                daysForecastAdapter.submitList(forecastlist)
            }
        }
    }

    override fun onDayForecastClicked(view: View?, mForecast: Forecast?) {

    }

}