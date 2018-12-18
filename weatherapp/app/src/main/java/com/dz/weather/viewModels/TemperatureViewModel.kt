package com.dz.weather.viewModels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean

class TemperatureViewModel : ViewModel() {
    val isCelsius: ObservableBoolean = ObservableBoolean(true)
}