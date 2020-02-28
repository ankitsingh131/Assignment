package com.app.weatherapp.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.weatherapp.R
import com.app.weatherapp.models.ForecastDataModel
import com.app.weatherapp.models.WeatherDataModel
import com.app.weatherapp.network.repositories.WeatherRetrofitRepository
import com.app.weatherapp.network.response.Status
import com.app.weatherapp.ui.adapters.ForecastAdapter
import com.app.weatherapp.utils.units
import com.app.weatherapp.utils.unitsSymbols
import com.app.weatherapp.viewModels.ViewModelFactory
import com.app.weatherapp.viewModels.WeatherViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val forecastAdapter = ForecastAdapter(unitsSymbols[spinnerUnits.selectedItemPosition])
        rvForecast.layoutManager = LinearLayoutManager(this)
        rvForecast.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        rvForecast.adapter = forecastAdapter

        val weatherRetrofitRepository = WeatherRetrofitRepository()

        val weatherViewModel =
            ViewModelProvider(this, ViewModelFactory(weatherRetrofitRepository)).get(
                WeatherViewModel::class.java
            )

        weatherViewModel.weatherLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    displayData(it.data)
                }
                Status.ERROR -> {
                    hideLoading()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        weatherViewModel.forecastLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    displayForecast(it.data)
                }
                Status.ERROR -> {
                    hideLoading()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        etZipCode.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                weatherViewModel.loadWeather(
                    "${v.text.trim()}",
                    units[spinnerUnits.selectedItemPosition]
                )
                true
            } else {
                false
            }
        }

        btnLoadWeather.setOnClickListener {
            if (etZipCode.text.trim().isEmpty()) {
                Toast.makeText(this@MainActivity, "Please enter zip code", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            weatherViewModel.loadWeather(
                "${etZipCode.text.trim()}",
                units[spinnerUnits.selectedItemPosition]
            )
        }

        btnLoadForecast.setOnClickListener {
            if (etZipCode.text.trim().isEmpty()) {
                Toast.makeText(this@MainActivity, "Please enter zip code", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            weatherViewModel.loadForecast(
                "${etZipCode.text.trim()}",
                units[spinnerUnits.selectedItemPosition]
            )
        }
    }

    private fun displayForecast(data: ForecastDataModel?) {
        group.visibility = View.GONE
        rvForecast.visibility = View.VISIBLE
        data?.let {
            (rvForecast.adapter as ForecastAdapter).submitList(data.list)
        }
    }

    private fun displayData(data: WeatherDataModel?) {
        rvForecast.visibility = View.GONE
        group.visibility = View.VISIBLE
        data?.let {
            val unitSymbol = unitsSymbols[spinnerUnits.selectedItemPosition]
            val weather = data.weather[0]
            Picasso.get().load("http://openweathermap.org/img/wn/${weather.icon}@2x.png")
            txtWeatherDescription.text = "${data.name}\n${weather.main}"
            txtWeatherOtherDescription.text =
                "Min: ${data.main.tempMin}${unitSymbol}-Max: ${data.main.tempMax}${unitSymbol}" +
                        "\nFeels like: ${data.main.feelsLike}$unitSymbol" +
                        "\nHumidity: ${data.main.humidity} %" +
                        "\nWind speed: ${data.wind.speed}"
        }
    }
}
