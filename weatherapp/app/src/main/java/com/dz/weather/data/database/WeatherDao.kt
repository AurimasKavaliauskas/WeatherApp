package com.dz.weather.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.dz.weather.data.weatherObjects.CurrentWeather

@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    fun insertWeather(weather: CurrentWeather)

    @Query("SELECT * FROM CurrentWeather")
    fun getAllWeathers(): LiveData<List<CurrentWeather>>

    @Query("SELECT id FROM CurrentWeather")
    fun getAllCitiesIds(): List<Int>

    @Transaction
    fun updateWeatherList(weatherList: List<CurrentWeather>) {
        for (weather in weatherList) {

            updateWeather(weather.getEntity())
        }
    }

    @Update(onConflict = REPLACE)
    fun updateWeather(weather: CurrentWeather)

    @Delete
    fun deleteWeather(weather: CurrentWeather)
}