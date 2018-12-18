package com.dz.weather.adapters

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dz.weather.R
import com.dz.weather.data.weatherObjects.CurrentWeather
import com.dz.weather.databinding.CityItemBinding
import com.dz.weather.utils.ImageLoader
import com.dz.weather.viewModels.TemperatureViewModel

class CityViewHolder(
        private val parent: ViewGroup,
        private val imageLoader: ImageLoader,
        private val binding: CityItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.city_item,
                parent, false)

) : RecyclerView.ViewHolder(binding.root) {

    var weather: CurrentWeather?
        get() {
            return binding.city
        }
        private set(value) {
            binding.city = value
        }

    fun bind(weather: CurrentWeather) {
        this.weather = weather
        binding.temperatureCheck =
                ViewModelProviders.of(parent.context as FragmentActivity).get(TemperatureViewModel::class.java)
        imageLoader.loadImage(weather.weatherEntity.icon, binding.imageView)
    }
}
