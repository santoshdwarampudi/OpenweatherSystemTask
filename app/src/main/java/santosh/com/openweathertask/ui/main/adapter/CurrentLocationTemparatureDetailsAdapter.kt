package santosh.com.openweathertask.ui.main.adapter

import DataList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import santosh.com.openweathertask.R
import santosh.com.openweathertask.data.model.currentCityModels.CurrentCityWeatherResponse


class CurrentLocationTemparatureDetailsAdapter(
    private val currentCityWeatherResponse: CurrentCityWeatherResponse
) : RecyclerView.Adapter<CurrentLocationTemparatureDetailsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(currentCityWeatherResponse: CurrentCityWeatherResponse, dataList: DataList) {
            itemView.apply {
                val textViewCityName = findViewById<TextView>(R.id.textViewCityName)
                val textViewTemparature = findViewById<TextView>(R.id.textViewTemparature)
                val textViewWeather = findViewById<TextView>(R.id.textViewWeather)
                val textViewWindSpeed = findViewById<TextView>(R.id.textViewWindSpeed)
                val textViewTime = findViewById<TextView>(R.id.textViewTime)
                textViewTime.visibility = View.VISIBLE
                textViewCityName.text = currentCityWeatherResponse.city.name
                val temp_min: Double = dataList.currentAddressMain.temp_min
                val temp_max: Double = dataList.currentAddressMain.temp_max
                textViewTemparature.text =
                    " $temp_min - $temp_max"
                textViewWeather.text = dataList.weather[0].description
                textViewWindSpeed.text = dataList.wind.speed.toString()
                textViewTime.text = dataList.dt_txt
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.currenttemparaturemodel, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return currentCityWeatherResponse.list.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(currentCityWeatherResponse, currentCityWeatherResponse.list[position])
    }

    fun addTempResponse(currentCityWeatherResponse: CurrentCityWeatherResponse) {
        this.currentCityWeatherResponse.apply {
            currentCityWeatherResponse
        }

    }
}