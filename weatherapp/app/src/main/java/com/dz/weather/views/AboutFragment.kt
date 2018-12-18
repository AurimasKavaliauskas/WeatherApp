package com.dz.weather.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dz.weather.R
import com.dz.weather.base.BaseApplication
import com.dz.weather.databinding.FragmentCityListBinding
import com.dz.weather.utils.CityItemTouchController
import com.dz.weather.viewModels.WeatherViewModel

class AboutFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }
}