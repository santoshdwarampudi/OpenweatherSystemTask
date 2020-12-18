package santosh.com.openweathertask.data.repository

import santosh.com.openweathertask.data.api.ApiHelper

class CurrentLocationRepository(private val apiHelper: ApiHelper) {
    suspend fun getCurrentDayLocationInfo(
        cityName: String,
        apiKey: String
    ) = apiHelper.getCurrentDayLocationInfo(cityName, apiKey)

    suspend fun getCurrentCityTemparatureInfo(
        lat: Double,
        long: Double,
        apiKey: String
    ) = apiHelper.getCurrentCityTemparatureInfo(lat, long, apiKey)
}