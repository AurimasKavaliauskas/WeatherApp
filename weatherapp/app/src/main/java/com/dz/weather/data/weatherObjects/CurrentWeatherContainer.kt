package com.dz.weather.data.weatherObjects

import android.arch.persistence.room.Ignore
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentWeatherContainer {
    @Ignore
    @SerializedName("list")
    @Expose
    var currentWeatherList: List<CurrentWeather> = emptyList()
}