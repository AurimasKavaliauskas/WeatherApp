package com.dz.weather.data.weatherObjects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {

    //    @SerializedName("id")
//    @Expose
//    var id: Int = 0
    @SerializedName("main")
    @Expose
    var main: String = ""
    @SerializedName("description")
    @Expose
    var description = ""
    @SerializedName("icon")
    @Expose
    var icon: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Weather

        if (main != other.main) return false
        if (description != other.description) return false
        if (icon != other.icon) return false

        return true
    }

    override fun hashCode(): Int {
        var result = main.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + icon.hashCode()
        return result
    }


}
