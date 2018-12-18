package com.dz.weather.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.dz.weather.adapters.CityListAdapter
import com.dz.weather.base.BaseApplication
import com.dz.weather.base.dependencyRetriever
import com.dz.weather.data.WeatherRepository
import com.dz.weather.data.weatherObjects.CurrentWeather

class WeatherViewModel(
        application: Application
) : AndroidViewModel(application) {

    private val repository: WeatherRepository = (application as BaseApplication).dependencyRetriever.weatherRepository
    val newCityName: ObservableField<String> = ObservableField("")
    var isRefreshing: ObservableBoolean = ObservableBoolean(false)
    var isAddingEnabled: ObservableBoolean = ObservableBoolean(true)
    val cities: LiveData<List<CurrentWeather>> = repository.getWeathers()
    val adapter: CityListAdapter = CityListAdapter(
            application.dependencyRetriever.imageLoader,
            repository)

    fun refreshVariables() {
        isAddingEnabled.set(adapter.itemCount == 0)
    }

    fun refresh() {
        if (!isRefreshing.get() && adapter.itemCount > 0) {
            isRefreshing.set(true)
            repository.refreshCityList(::finishRefreshing)
        }
    }

    fun addNewCity(cityName: String) {
        if (!isRefreshing.get()) {
            repository.addCity(cityName, ::cityAdded)
        }
    }

    private fun cityAdded() {
        newCityName.set("")
    }

    private fun finishRefreshing() {
        isRefreshing.set(false)
    }
}