package com.app.weatherapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.weatherapp.network.repositories.WeatherRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val weatherRepository: WeatherRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(WeatherViewModel::class.java) ->
                    WeatherViewModel(weatherRepository)
                else ->
                    throw IllegalArgumentException("ViewModel class (${modelClass.name}) is not mapped")
            }
        } as T
}

