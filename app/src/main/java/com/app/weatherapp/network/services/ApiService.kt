package com.app.weatherapp.network.services

import com.app.weatherapp.BuildConfig
import com.app.weatherapp.models.ForecastDataModel
import com.app.weatherapp.models.WeatherDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather")
    fun getWeather(@Query("zip") zip: String, @Query("units") unit: String, @Query("APPID") appId: String = BuildConfig.APP_ID): Call<WeatherDataModel>

    @GET("data/2.5/forecast")
    fun getForecast(@Query("zip") zip: String, @Query("units") unit: String, @Query("APPID") appId: String = BuildConfig.APP_ID): Call<ForecastDataModel>
}