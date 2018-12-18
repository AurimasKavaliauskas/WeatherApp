package com.dz.weather.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.dz.weather.data.CityRemover
import com.dz.weather.data.weatherObjects.CurrentWeather
import com.dz.weather.utils.ImageLoader


class CityListAdapter(
        private val imageLoader: ImageLoader,
        private val cityRemover: CityRemover,
        private var cities: List<CurrentWeather> = emptyList()
) : RecyclerView.Adapter<CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CityViewHolder {
        return CityViewHolder(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position])
        holder.itemView.setOnLongClickListener { _ ->
            holder.weather?.let { cityRemover.deleteCity(it) }
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount() = cities.size

    fun update(newData: List<CurrentWeather>) {
        cities = newData
        notifyDataSetChanged()
    }
}