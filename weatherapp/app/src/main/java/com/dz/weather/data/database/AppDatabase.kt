package com.dz.weather.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dz.weather.data.weatherObjects.CurrentWeather

@Database(
        entities =
        [
            (CurrentWeather::class)
        ],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}