package santosh.com.openweathertask.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import santosh.com.openweathertask.data.api.ApiHelperImpelmentation
import santosh.com.openweathertask.data.repository.CurrentLocationRepository
import santosh.com.openweathertask.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelperImpelmentation) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(CurrentLocationRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}