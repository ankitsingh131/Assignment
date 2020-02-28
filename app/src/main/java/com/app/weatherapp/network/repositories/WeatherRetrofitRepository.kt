package com.app.weatherapp.network.repositories

import androidx.lifecycle.MutableLiveData
import com.app.weatherapp.models.ForecastDataModel
import com.app.weatherapp.models.WeatherDataModel
import com.app.weatherapp.network.RetrofitClient
import com.app.weatherapp.network.response.Result
import com.app.weatherapp.utils.isUnitsCorrect
import com.app.weatherapp.utils.isZipCodeValid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRetrofitRepository : WeatherRepository {

    private val apiService = RetrofitClient.apiService

    override fun getWeather(
        weatherLiveData: MutableLiveData<Result<WeatherDataModel>>,
        zip: String,
        unit: String
    ) {
        if (!isZipCodeValid(zip))
            weatherLiveData.postValue(Result.error(message = "Please enter zip code"))
        else if (!isUnitsCorrect(unit))
            weatherLiveData.postValue(Result.error(message = "Select valid unit"))
        else {
            weatherLiveData.postValue(Result.loading("Loading"))
            val call = apiService.getWeather("$zip,in", unit)
            call.enqueue(object : Callback<WeatherDataModel> {
                override fun onFailure(call: Call<WeatherDataModel>, e: Throwable) {
                    weatherLiveData.postValue(
                        Result.error(
                            message = e.message ?: "Something went wrong"
                        )
                    )
                }

                override fun onResponse(
                    call: Call<WeatherDataModel>,
                    response: Response<WeatherDataModel>
                ) {
                    if (response.isSuccessful) {
                        weatherLiveData.postValue(
                            Result.success(
                                data = response.body()
                            )
                        )
                    } else {
                        weatherLiveData.postValue(Result.error(message = "Something went wrong"))
                    }
                }

            })
        }
    }

    override fun getForecast(
        weatherLiveData: MutableLiveData<Result<ForecastDataModel>>,
        zip: String,
        unit: String
    ) {
        if (!isZipCodeValid(zip))
            weatherLiveData.postValue(Result.error(message = "Please enter zip code"))
        else if (!isUnitsCorrect(unit))
            weatherLiveData.postValue(Result.error(message = "Select valid unit"))
        else {
            weatherLiveData.postValue(Result.loading("Loading"))
            val call = apiService.getForecast("$zip,in", unit)
            call.enqueue(object : Callback<ForecastDataModel> {
                override fun onFailure(call: Call<ForecastDataModel>, e: Throwable) {
                    weatherLiveData.postValue(
                        Result.error(
                            message = e.message ?: "Something went wrong"
                        )
                    )
                }

                override fun onResponse(
                    call: Call<ForecastDataModel>,
                    response: Response<ForecastDataModel>
                ) {
                    if (response.isSuccessful) {
                        weatherLiveData.postValue(
                            Result.success(
                                data = response.body()
                            )
                        )
                    } else {
                        weatherLiveData.postValue(Result.error(message = "Something went wrong"))
                    }
                }

            })
        }
    }
}