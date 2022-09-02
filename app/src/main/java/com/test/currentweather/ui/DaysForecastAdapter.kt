package com.test.currentweather.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.currentweather.databinding.ItemDayForecastBinding
import com.test.weathersdk.model.Forecast
import java.text.SimpleDateFormat
import java.util.*

class DaysForecastAdapter(private val listener: ForecastAdapterListener) :
    ListAdapter<Forecast, DaysForecastAdapter.DayForecastViewHolder?>(ForecastDiff) {

    interface ForecastAdapterListener {
        fun onDayForecastClicked(view: View?, mForecast: Forecast?)
    }

    var itemDayForecastBinding: ItemDayForecastBinding? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DayForecastViewHolder {
        itemDayForecastBinding = ItemDayForecastBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return DayForecastViewHolder(itemDayForecastBinding!!, listener)
    }

    override fun onBindViewHolder(searchCityViewHolder: DayForecastViewHolder, i: Int) {
        searchCityViewHolder.bind(getItem(i))
    }

    class DayForecastViewHolder(
        itemDayForecastBinding: ItemDayForecastBinding,
        private val listener: ForecastAdapterListener
    ) :
        RecyclerView.ViewHolder(itemDayForecastBinding.getRoot()) {
        var itemDayForecastBinding: ItemDayForecastBinding
        fun bind(forecast: Forecast) {
            itemDayForecastBinding.mcvWeatherMain.setOnClickListener(View.OnClickListener {
                listener.onDayForecastClicked(
                    itemDayForecastBinding.mcvWeatherMain,
                    forecast
                )
            })

            itemDayForecastBinding.txtForecastDate.setText(getFormattedDate(forecast.dt))
            val temp = "${forecast.main.temp}".substringBefore(".") + "°C"
            itemDayForecastBinding.txtTempOfCity.setText(temp)
            itemDayForecastBinding.txtWeatherType.setText(forecast.weather.get(0).main)
            Glide.with(itemDayForecastBinding.imgWeatherTypeIcon.getContext())
                .load(getWeatherIconURL(forecast.weather.get(0).icon))
                .into(itemDayForecastBinding.imgWeatherTypeIcon)
        }

        init {
            this.itemDayForecastBinding = itemDayForecastBinding
        }

        fun getFormattedDate(unixTimestamp: Int): String {
            try {
                val date = Date(unixTimestamp * 1000L)
                val sdf = SimpleDateFormat("dd, MMM", Locale.getDefault())
                sdf.timeZone = TimeZone.getDefault()
                return sdf.format(date)
            } catch (e: Exception) {
                Log.e("DEBUG", "getFormattedDate", e)
            }
            return ""
        }

        fun getWeatherIconURL(icon: String): String {
            return "https://openweathermap.org/img/wn/${icon}@2x.png"
        }
    }

    object ForecastDiff : DiffUtil.ItemCallback<Forecast>() {
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return oldItem.dt.equals(newItem.dt)
        }
    }


}
