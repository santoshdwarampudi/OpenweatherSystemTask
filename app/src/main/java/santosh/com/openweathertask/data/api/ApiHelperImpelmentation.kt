package santosh.com.openweathertask.data.api

class ApiHelperImpelmentation(private val apiService: ApiService) : ApiHelper {
    override suspend fun getCurrentDayLocationInfo(
        cityName: String,
        apiKey: String
    ) =
        apiService.getCurrentLocationInfo(cityName, apiKey)

    override suspend fun getCurrentCityTemparatureInfo(
        lat: Double,
        long: Double,
        apiKey: String
    ) = apiService.getCurrentCityTemparatureDetails(lat, long, apiKey)


}