package com.dz.weather.utils

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.*
import com.dz.weather.adapters.CityViewHolder
import com.dz.weather.data.CityRemover

class CityItemTouchController(private val cityRemover: CityRemover) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int {
        return ItemTouchHelper.Callback.makeMovementFlags(UP or DOWN, LEFT or RIGHT)
    }

    override fun onMove(p0: RecyclerView, vh: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(vh: RecyclerView.ViewHolder, p1: Int) {
        (vh as CityViewHolder).weather?.let { cityRemover.deleteCity(it) }
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }
}