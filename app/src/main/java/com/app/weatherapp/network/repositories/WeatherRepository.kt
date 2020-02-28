package com.app.weatherapp.network.repositories

import androidx.lifecycle.MutableLiveData
import com.app.weatherapp.models.ForecastDataModel
import com.app.weatherapp.models.WeatherDataModel
import com.app.weatherapp.network.response.Result


interface WeatherRepository {

    fun getWeather(weatherLiveData: MutableLiveData<Result<WeatherDataModel>>, zip: String, unit: String)

    fun getForecast(weatherLiveData: MutableLiveData<Result<ForecastDataModel>>, zip: String, unit: String)
}