package com.dz.weather.base

import android.arch.persistence.room.Room
import android.content.Context
import com.dz.weather.BuildConfig
import com.dz.weather.data.WeatherRepository
import com.dz.weather.data.api.WeatherAPI
import com.dz.weather.data.database.AppDatabase
import com.dz.weather.utils.ImageLoader
import com.dz.weather.utils.PicassoImageLoader
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DependencyRetriever(private val context: Context) {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    private val api: WeatherAPI by lazy {
        Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(WeatherAPI::class.java)

    }

    private val appDatabase by lazy {
        Room
                .databaseBuilder(context, AppDatabase::class.java, "db")
                .fallbackToDestructiveMigration()
                .build()
    }

    private val executor: Executor by lazy {
        Executors.newSingleThreadExecutor()
    }

    val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(appDatabase.weatherDao(), api, executor)
    }

    val imageLoader: ImageLoader by lazy {
        PicassoImageLoader(BuildConfig.API_BASE_IMAGE_URL)
    }
}

val Context.dependencyRetriever: DependencyRetriever get() = (applicationContext as BaseApplication).dependencyRetriever