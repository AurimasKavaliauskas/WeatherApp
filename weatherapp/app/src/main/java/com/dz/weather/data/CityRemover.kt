package com.dz.weather.data

import com.dz.weather.data.weatherObjects.CurrentWeather

interface CityRemover {
    fun deleteCity(city: CurrentWeather)
}