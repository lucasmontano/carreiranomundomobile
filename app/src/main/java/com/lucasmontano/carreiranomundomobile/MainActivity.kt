package com.lucasmontano.carreiranomundomobile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.lucasmontano.carreiranomundomobile.collections.HabitListViewModel
import com.lucasmontano.carreiranomundomobile.databinding.ActivityMainBinding
import com.lucasmontano.carreiranomundomobile.dummy.MockHabits

class MainActivity : AppCompatActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding

  private val viewModel: HabitListViewModel by viewModels {
    HabitListViewModel.Factory(MockHabits)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)

    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)
    setupNavigation()

    binding.fab.setOnClickListener { _ ->
      viewModel.addRandomHabit()
    }
  }

  private fun setupNavigation() {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp(appBarConfiguration)
        || super.onSupportNavigateUp()
  }
}
