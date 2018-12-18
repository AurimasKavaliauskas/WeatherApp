package com.dz.weather.viewModels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class ToolBarViewModel : ViewModel() {
    val toolbarTitle: ObservableField<String> = ObservableField("")
}