package com.app.weatherapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.weatherapp.models.ForecastDataModel
import com.app.weatherapp.models.WeatherDataModel
import com.app.weatherapp.network.repositories.WeatherRepository
import com.app.weatherapp.network.response.Result

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val weatherLiveData = MutableLiveData<Result<WeatherDataModel>>()

    val forecastLiveData = MutableLiveData<Result<ForecastDataModel>>()

    fun loadWeather(zip: String, unit: String = "metric") = weatherRepository.getWeather(weatherLiveData, zip, unit)

    fun loadForecast(zip: String, unit: String = "metric") = weatherRepository.getForecast(forecastLiveData, zip, unit)
}