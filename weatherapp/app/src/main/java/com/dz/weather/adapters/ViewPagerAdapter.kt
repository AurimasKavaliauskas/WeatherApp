package com.dz.weather.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.dz.weather.views.CityListFragment
import com.dz.weather.views.SettingsFragment
//import com.dz.weather.views.AboutFragment

class ViewPagerAdapter(
        manager: FragmentManager,
        private val citiesTitle: String,
        private val settingsTitle: String//,
    //    private val aboutTitle: String
) : FragmentStatePagerAdapter(manager) {

    override fun getItem(p0: Int) =
            when (p0) {
                0 -> CityListFragment.newInstance()
                else -> SettingsFragment.newInstance()
               // else ->AboutFragment.newInstance()
            }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int) =
            when (position) {
                0 -> citiesTitle
                else -> settingsTitle
               // else->aboutTitle
            }
}