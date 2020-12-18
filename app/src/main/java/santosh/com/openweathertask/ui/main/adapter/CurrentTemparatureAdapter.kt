package santosh.com.openweathertask.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import santosh.com.openweathertask.R
import santosh.com.openweathertask.data.model.CurrentWeatherResponse

class CurrentTemparatureAdapter(
    private val currentWeatherResponse: ArrayList<CurrentWeatherResponse>
) :
    RecyclerView.Adapter<CurrentTemparatureAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(currentWeatherResponse: CurrentWeatherResponse) {
            itemView.apply {
                val textViewCityName = findViewById<TextView>(R.id.textViewCityName)
                val textViewTemparature = findViewById<TextView>(R.id.textViewTemparature)
                val textViewWeather = findViewById<TextView>(R.id.textViewWeather)
                val textViewWindSpeed = findViewById<TextView>(R.id.textViewWindSpeed)
                textViewCityName.text = currentWeatherResponse.name
                val temp_min: Double = currentWeatherResponse.main.temp_min
                val temp_max: Double = currentWeatherResponse.main.temp_max
                textViewTemparature.text =
                    " $temp_min - $temp_max"
                textViewWeather.text = currentWeatherResponse.weather[0].description
                textViewWindSpeed.text = currentWeatherResponse.wind.speed.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
       return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.currenttemparaturemodel  , parent, false))
    }

    override fun getItemCount(): Int {
        return currentWeatherResponse.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(currentWeatherResponse[position])
    }

    fun addTempResponse(currentWeatherResponse: List<CurrentWeatherResponse>) {
        this.currentWeatherResponse.apply {
            clear()
            addAll(currentWeatherResponse)
        }

    }
}