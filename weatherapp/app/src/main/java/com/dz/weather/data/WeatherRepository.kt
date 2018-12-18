package com.dz.weather.data

import android.arch.lifecycle.LiveData
import com.dz.weather.data.api.WeatherAPI
import com.dz.weather.data.database.WeatherDao
import com.dz.weather.data.weatherObjects.CurrentWeather
import com.dz.weather.data.weatherObjects.CurrentWeatherContainer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class WeatherRepository(
        private val weatherDao: WeatherDao,
        private val api: WeatherAPI,
        private val executor: Executor
) : CityRemover {

    fun addCity(city: String, cityAdded: () -> Unit) {
        executor.execute {
            api.getWeather(city).enqueue(object : Callback<CurrentWeather> {
                override fun onFailure(call: Call<CurrentWeather>?, t: Throwable?) {
                    print("Callback failure")
                }

                override fun onResponse(call: Call<CurrentWeather>?, response: Response<CurrentWeather>?) {
                    if (response?.body() != null) {
                        cityAdded()
                        insertWeather(response.body()!!.getEntity())
                    } else {
                        print("Response error")
                    }
                }
            })
        }
    }

    fun refreshCityList(finishRefreshing: () -> Unit) {

        //TODO: WEATHER API HAS A LIMIT OF 20 LOCATIONS. NEED TO SPLIT CALLS
        executor.execute {
            api.getCities(convertIntListToString(weatherDao.getAllCitiesIds())).enqueue(object : Callback<CurrentWeatherContainer> {
                override fun onFailure(call: Call<CurrentWeatherContainer>?, t: Throwable?) {
                    finishRefreshing()
                    print("Callback failure")
                }

                override fun onResponse(call: Call<CurrentWeatherContainer>?, response: Response<CurrentWeatherContainer>?) {
                    finishRefreshing()
                    if (response?.body() != null) {
                        updateWeatherList(response.body()!!.currentWeatherList)

                    } else {
                        print("Response error")
                    }
                }
            })
        }
    }

    fun insertWeather(weather: CurrentWeather) {
        executor.execute { weatherDao.insertWeather(weather) }
    }

    fun updateWeatherList(weatherList: List<CurrentWeather>) {
        executor.execute { weatherDao.updateWeatherList(weatherList) }
    }

    override fun deleteCity(city: CurrentWeather) {
        executor.execute {
            weatherDao.deleteWeather(city)
        }
    }

    fun getWeathers(): LiveData<List<CurrentWeather>> {
        return weatherDao.getAllWeathers()
    }

    private fun convertIntListToString(ints: List<Int>): String {
        var str = ""
        for (i in ints) {
            str += ",$i"
        }
        return str.substring(1)
    }
}