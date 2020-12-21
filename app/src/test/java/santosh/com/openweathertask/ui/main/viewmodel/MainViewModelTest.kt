package santosh.com.openweathertask.ui.main.viewmodel

import Clouds
import Coord
import CurrentAddressMain
import CurrentAddressSys
import Wind
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import santosh.com.openweathertask.data.api.ApiHelper
import santosh.com.openweathertask.data.api.ApiHelperImpelmentation
import santosh.com.openweathertask.data.model.CurrentWeatherResponse
import santosh.com.openweathertask.data.repository.CurrentLocationRepository
import santosh.com.openweathertask.ui.main.utils.TestCoroutineRule
import santosh.com.openweathertask.utils.Resource

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<CurrentWeatherResponse>>

    private lateinit var currentWeatherResponse: CurrentWeatherResponse

    @Before
    fun setUp() {
        val coord = Coord(17.11, 78.33)
        val currentAddressMain = CurrentAddressMain(
            0.0, 0.0,
            0.0, 0.0, 0, 0, 0, 0, 0.0
        )
        val wind = Wind(0.0, 0)
        val clouds = Clouds(0)
        val currentAddressSys = CurrentAddressSys("")
        currentWeatherResponse =
            CurrentWeatherResponse(
                coord, emptyList(), "", currentAddressMain, 0, wind, clouds,
                0, currentAddressSys, 0, 0, "", 0
            )
    }

    @Test
    fun getCurrentDayLocationInfoSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(currentWeatherResponse)
                .`when`(apiHelper)
                .getCurrentDayLocationInfo("chennai", "b68e08913779897c68435f55c2512a2c")
            val viewModel =
                MainViewModel(currentLocationRepository = CurrentLocationRepository(apiHelper))
            viewModel.getCurrentDayLocationInfo("chennai", "b68e08913779897c68435f55c2512a2c")
                .observeForever(apiUsersObserver)
            verify(apiHelper).getCurrentDayLocationInfo(
                "chennai",
                "b68e08913779897c68435f55c2512a2c"
            )
            verify(apiUsersObserver).onChanged(Resource.success(currentWeatherResponse))
            viewModel.getCurrentDayLocationInfo("chennai", "b68e08913779897c68435f55c2512a2c")
                .removeObserver(apiUsersObserver)
        }

    }

    @Test
    fun getCurrentDayLocationInfoError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getCurrentDayLocationInfo("chennai", "b68e08913779897c68435f55c2512a2c")
            val viewModel =
                MainViewModel(currentLocationRepository = CurrentLocationRepository(apiHelper))
            viewModel.getCurrentDayLocationInfo("chennai", "b68e08913779897c68435f55c2512a2c")
                .observeForever(apiUsersObserver)
            verify(apiHelper).getCurrentDayLocationInfo(
                "chennai",
                "b68e08913779897c68435f55c2512a2c"
            )
            verify(apiUsersObserver).onChanged(
                Resource.error(
                    data = null,
                    message = RuntimeException(errorMessage).toString()
                )
            )
            viewModel.getCurrentDayLocationInfo("chennai", "b68e08913779897c68435f55c2512a2c")
                .removeObserver(apiUsersObserver)
        }
    }

    companion object {

    }
}



