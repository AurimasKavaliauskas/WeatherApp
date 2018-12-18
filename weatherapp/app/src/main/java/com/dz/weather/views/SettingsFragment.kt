package com.dz.weather.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dz.weather.R
import com.dz.weather.base.BaseApplication
import com.dz.weather.databinding.FragmentSettingsBinding
import com.dz.weather.viewModels.TemperatureViewModel
import com.dz.weather.viewModels.WeatherViewModel

class SettingsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSettingsBinding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.fragment_settings,
                        container,
                        false
                )

        binding.setLifecycleOwner(this)

        val weatherViewModel: WeatherViewModel =
                ViewModelProviders.of(this).get(WeatherViewModel(activity?.application as BaseApplication)::class.java)

        binding.temperatureCheck =
                this.activity?.let { ViewModelProviders.of(it).get(TemperatureViewModel::class.java) }

        weatherViewModel.cities.observe(this,
                Observer { cityList ->
                    cityList?.let { weatherViewModel.adapter.update(it) }
                    weatherViewModel.refreshVariables()
                })

        binding.weatherViewModel = weatherViewModel

        return binding.root
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}