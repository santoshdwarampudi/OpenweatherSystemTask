package santosh.com.openweathertask.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import santosh.com.openweathertask.data.model.CurrentWeatherResponse
import santosh.com.openweathertask.data.model.currentCityModels.CurrentCityWeatherResponse

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentLocationInfo(
        @Query("q") cityName: String,
        @Query("appid") appid: String
    ): CurrentWeatherResponse

    @GET("data/2.5/forecast")
    suspend fun getCurrentCityTemparatureDetails(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): CurrentCityWeatherResponse
}