package santosh.com.openweathertask.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import santosh.com.openweathertask.data.repository.CurrentLocationRepository
import santosh.com.openweathertask.utils.Resource

class MainViewModel(private val currentLocationRepository: CurrentLocationRepository) :
    ViewModel() {
    fun getCurrentDayLocationInfo(
        cityName: String,
        apiKey: String
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = currentLocationRepository.getCurrentDayLocationInfo(
                        cityName,
                        apiKey
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getCurrentCityTemparatureInfo(
        lat: Double,
        long: Double,
        apiKey: String
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = currentLocationRepository.getCurrentCityTemparatureInfo(
                        lat, long, apiKey
                    )

                )
            )

        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}