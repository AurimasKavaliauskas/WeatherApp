package com.dz.weather.data.weatherObjects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sys {

    @SerializedName("type")
    @Expose
    var type: Int = 0
    //    @SerializedName("id")
//    @Expose
//    var id: Int = 0
    @SerializedName("message")
    @Expose
    var message: Double = -1.0
    @SerializedName("country")
    @Expose
    var country: String = ""
    @SerializedName("sunrise")
    @Expose
    var sunrise: Int = 0
    @SerializedName("sunset")
    @Expose
    var sunset: Int = 0

}
