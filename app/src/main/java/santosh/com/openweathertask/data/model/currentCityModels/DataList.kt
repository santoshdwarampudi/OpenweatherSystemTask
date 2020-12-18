import com.google.gson.annotations.SerializedName




data class DataList (

	@SerializedName("dt") val dt : Int,
	@SerializedName("main") val currentAddressMain : CurrentAddressMain,
	@SerializedName("weather") val weather : List<Weather>,
	@SerializedName("clouds") val clouds : Clouds,
	@SerializedName("wind") val wind : Wind,
	@SerializedName("visibility") val visibility : Int,
	@SerializedName("pop") val pop : Int,
	@SerializedName("sys") val currentAddressSys : CurrentAddressSys,
	@SerializedName("dt_txt") val dt_txt : String
)