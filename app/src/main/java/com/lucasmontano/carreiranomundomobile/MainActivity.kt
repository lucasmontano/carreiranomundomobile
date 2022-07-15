package com.lucasmontano.carreiranomundomobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.lucasmontano.carreiranomundomobile.databinding.ActivityMainBinding
import com.lucasmontano.openweather.DummyWeatherForecast

class MainActivity : AppCompatActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.toolbar)

    val navController = findNavController(R.id.nav_host_fragment_content_main)
    appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)

    binding.fab.setOnClickListener { view ->
      val currentCity = DummyWeatherForecast.currentCity
      val forecast = DummyWeatherForecast.forecast()
      val message = when {
        forecast.isRaining -> getString(R.string.is_raining_message, currentCity)
        !forecast.isRaining -> getString(R.string.is_not_raining_message, currentCity)
        else -> getString(R.string.forecast_not_available_message, currentCity)
      }
      Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp(appBarConfiguration)
        || super.onSupportNavigateUp()
  }
}