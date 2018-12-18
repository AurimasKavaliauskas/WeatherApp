package com.dz.weather.utils

import android.widget.ImageView
import com.dz.weather.R
import com.squareup.picasso.Picasso

class PicassoImageLoader(private val baseUrl: String) : ImageLoader {
    private val picasso = Picasso.get()
    override fun loadImage(url: String, imageView: ImageView) {
        picasso.load("$baseUrl$url.png").error(R.mipmap.ic_launcher).resize(200, 200).into(imageView)
    }
}