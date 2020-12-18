package santosh.com.openweathertask.data.model.currentCityModels

import City
import DataList
import com.google.gson.annotations.SerializedName

class CurrentCityWeatherResponse (
    @SerializedName("cod") val cod : Int,
    @SerializedName("message") val message : Int,
    @SerializedName("cnt") val cnt : Int,
    @SerializedName("list") val list : List<DataList>,
    @SerializedName("city") val city : City
)