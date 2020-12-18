package santosh.com.openweathertask.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getCurrentDayLocationInfo(
        cityName: String,
        apiKey: String
    ) =
        apiService.getCurrentLocationInfo(cityName, apiKey)

    suspend fun getCurrentCityTemparatureInfo(
        lat: Double,
        long: Double,
        apiKey: String
    ) = apiService.getCurrentCityTemparatureDetails(lat, long, apiKey)


}