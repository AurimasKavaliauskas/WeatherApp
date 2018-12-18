package com.dz.weather.data.weatherObjects

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class CurrentWeather {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("name")
    @Expose
    var name: String = ""

    @SerializedName("visibility")
    @Expose
    var visibility: Int = 0

    @SerializedName("dt")
    @Expose
    var dt: Int = 0

    @SerializedName("cod")
    @Expose
    var cod: Int = 0

    @SerializedName("base")
    @Expose
    var base: String = ""

    @Embedded
    @SerializedName("coord")
    @Expose
    var coord: Coord = Coord()

    @Embedded
    @SerializedName("main")
    @Expose
    var main: Main = Main()

    @Embedded
    @SerializedName("wind")
    @Expose
    var wind: Wind = Wind()

    @Embedded
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds = Clouds()

    @Embedded
    @SerializedName("sys")
    @Expose
    var sys: Sys = Sys()

    @Embedded
    var weatherEntity: Weather = Weather()

    @Ignore
    @SerializedName("weather")
    @Expose
    var weatherList: List<Weather> = listOf(Weather())

    fun getEntity(): CurrentWeather {
        if (weatherEntity == Weather()) {
            weatherEntity = weatherList[0]
        }
        return this
    }

}
