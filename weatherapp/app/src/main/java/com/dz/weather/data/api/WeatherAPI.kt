package com.dz.weather.data.api

import com.dz.weather.data.weatherObjects.CurrentWeather
import com.dz.weather.data.weatherObjects.CurrentWeatherContainer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    fun getWeather(@Query("q") city: String, @Query("appid") apiKey: String = API_KEY): Call<CurrentWeather>

    @GET("group")
    fun getCities(@Query("id") cityIds: String, @Query("appid") apiKey: String = API_KEY): Call<CurrentWeatherContainer>

    companion object {
        const val API_KEY = "1543fd357fbb28fd95457875f1a1e886"
    }
}