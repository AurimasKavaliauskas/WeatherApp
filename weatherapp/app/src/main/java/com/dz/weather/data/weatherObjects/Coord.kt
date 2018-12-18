package com.dz.weather.data.weatherObjects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coord {

    @SerializedName("lon")
    @Expose
    var lon: Double = -1.0
    @SerializedName("lat")
    @Expose
    var lat: Double = -1.0

}
