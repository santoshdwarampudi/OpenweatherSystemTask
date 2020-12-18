package santosh.com.openweathertask.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import santosh.com.openweathertask.R
import santosh.com.openweathertask.data.api.ApiHelper
import santosh.com.openweathertask.data.api.RetrofitBuilder
import santosh.com.openweathertask.data.model.CurrentWeatherResponse
import santosh.com.openweathertask.ui.base.ViewModelFactory
import santosh.com.openweathertask.ui.main.adapter.CurrentTemparatureAdapter
import santosh.com.openweathertask.ui.main.viewmodel.MainViewModel
import santosh.com.openweathertask.utils.Status

class CurrentTemparatureDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    val apiKey: String = "b68e08913779897c68435f55c2512a2c"
    private lateinit var getInfoButton: Button
    private lateinit var cityNames: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var currentWeatherResponseList: MutableList<CurrentWeatherResponse>
    private lateinit var currentTemparatureAdapter: CurrentTemparatureAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        currentWeatherResponseList = ArrayList()
        getInfoButton = findViewById(R.id.getInfoButton)
        cityNames = findViewById(R.id.cityNames)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        getInfoButton.setOnClickListener {
            currentWeatherResponseList.clear()
            val enterdCities = cityNames.text.trim()
            if (enterdCities.isNullOrEmpty()) {
                Toast.makeText(this, "Enter CityNames", Toast.LENGTH_LONG).show()
            } else {
                //check min 3 cities are there or not
                val splitedCities = enterdCities.split(",")
                if (splitedCities.size < 3 || splitedCities.size > 7) {
                    Toast.makeText(
                        this,
                        "City Count should be in between 3 and 7",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    recyclerView.visibility = View.VISIBLE
                    for (cityName in splitedCities) {
                        setupObservers(cityName)
                    }
                }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        currentTemparatureAdapter = CurrentTemparatureAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = currentTemparatureAdapter
    }

    private fun setupObservers(cityName: String) {
        viewModel.getCurrentDayLocationInfo(cityName, apiKey = apiKey)
            .observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { currentWeatherResponse ->
                                currentWeatherResponseList.add(currentWeatherResponse)
                                updateAdapter(currentWeatherResponseList)
                            }
                        }
                        Status.ERROR -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            })
    }

    private fun updateAdapter(currentWeatherResponseList: MutableList<CurrentWeatherResponse>) {
        currentTemparatureAdapter.apply {
            addTempResponse(currentWeatherResponseList)
            notifyDataSetChanged()
        }
    }

}