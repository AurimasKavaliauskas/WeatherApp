package com.dz.weather

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.dz.weather.adapters.ViewPagerAdapter
import com.dz.weather.databinding.ActivityMainBinding
import com.dz.weather.viewModels.ToolBarViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel: ToolBarViewModel =
                ViewModelProviders.of(this).get(ToolBarViewModel::class.java)

        val pager = binding.pager

        pager.adapter = ViewPagerAdapter(
                supportFragmentManager,
                getString(R.string.cities),
                getString(R.string.settings)
            // getString(R.string.about)
        )
        viewModel.toolbarTitle.set((pager.adapter as ViewPagerAdapter).getPageTitle(pager.currentItem))

        pager.addOnPageChangeListener(
                object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(p0: Int) {

                    }

                    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

                    }

                    override fun onPageSelected(p0: Int) {
                        viewModel.toolbarTitle.set(pager.adapter?.getPageTitle(p0).toString())
                    }
                }
        )

        binding.toolbarViewModel = viewModel

    }
}
