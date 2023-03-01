package com.lucasmontano.carreiranomundomobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lucasmontano.carreiranomundomobile.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController
  private lateinit var bottomNavigation: BottomNavigationView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)

    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)
    setupNavigation()
    setupBottomNavigation()

    // Getting app link information
    val appLinkIntent: Intent = intent
    val appLinkData: Uri? = appLinkIntent.data

    // Handling deep link to backlog screen
    if (appLinkData?.path.equals("/backlog")) {
      navController.navigate(R.id.action_habitListFragment_to_habitBacklogListFragment)
    }
  }

  private fun setupBottomNavigation() {
    bottomNavigation = binding.root.findViewById(R.id.navigationView)
    NavigationUI.setupWithNavController(bottomNavigation, navController)
  }

  private fun setupNavigation() {
    navController = findNavController(R.id.nav_host_fragment_content_main)
    appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }
}