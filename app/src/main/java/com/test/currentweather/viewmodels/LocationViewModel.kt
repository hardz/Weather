package com.test.currentweather.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LocationViewModel : ViewModel() {

    private var _currentLocation: MutableLiveData<LocationData>? = null

    fun setCurrentLocation(location: LocationData) {
        _currentLocation?.value = location
    }

    fun getCurrentLocation(): MutableLiveData<LocationData>? {
       return _currentLocation
    }
}
