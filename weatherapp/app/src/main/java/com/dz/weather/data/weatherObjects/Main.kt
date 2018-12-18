package com.dz.weather.data.weatherObjects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

class Main {

    @SerializedName("temp")
    @Expose
    var temp: Double = -1.0
    @SerializedName("pressure")
    @Expose
    var pressure: Int = 1
    @SerializedName("humidity")
    @Expose
    var humidity: Int = 1
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double = -1.0
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double = -1.0

    fun getCelsiusString(): String {
        return (temp - 273.15).roundToInt().toString()
    }

    fun getFahrenheitString(): String {
        return (9.0 / 5.0 * temp - 459.67).roundToInt().toString()
    }

}
