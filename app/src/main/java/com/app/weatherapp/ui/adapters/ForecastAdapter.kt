package com.app.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.weatherapp.R
import com.app.weatherapp.models.ForecastDataModel

class ForecastAdapter(private val unitSymbol: String) :
    RecyclerView.Adapter<ForecastAdapter.Holder>() {

    private var list: List<ForecastDataModel.Forecast> = ArrayList()

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        val txtWeatherDescription = view.findViewById<TextView>(R.id.txtWeatherDescription)
        val txtWeatherOtherDescription =
            view.findViewById<TextView>(R.id.txtWeatherOtherDescription)
    }

    fun submitList(list: List<ForecastDataModel.Forecast>) {
        this.list = list;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_forecast,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val forecast = list[position]
        holder.txtWeatherDescription.text = "${forecast.weather[0].description} ${forecast.dtTxt}"
        holder.txtWeatherOtherDescription.text =
            "Min: ${forecast.main.tempMin}${unitSymbol}-Max: ${forecast.main.tempMax}${unitSymbol}" +
                    "\nFeels like: ${forecast.main.feelsLike}$unitSymbol" +
                    "\nHumidity: ${forecast.main.humidity} %" +
                    "\nWind speed: ${forecast.wind.speed}"
    }

}