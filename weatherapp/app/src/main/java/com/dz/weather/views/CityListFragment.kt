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

class CityListFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCityListBinding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.fragment_city_list,
                        container,
                        false
                )

        binding.setLifecycleOwner(this)

        ItemTouchHelper(
                CityItemTouchController((this.activity?.application as BaseApplication)
                        .dependencyRetriever.weatherRepository))
                .attachToRecyclerView(binding.recyclerView)

        val viewModel: WeatherViewModel =
                ViewModelProviders.of(this).get(WeatherViewModel(activity?.application as BaseApplication)::class.java)

        viewModel.cities.observe(this,
                Observer { cityList ->
                    cityList?.let { viewModel.adapter.update(it) }
                    viewModel.refreshVariables()
                })

        binding.weatherViewModel = viewModel

        return binding.root
    }

    companion object {
        fun newInstance() = CityListFragment()
    }
}