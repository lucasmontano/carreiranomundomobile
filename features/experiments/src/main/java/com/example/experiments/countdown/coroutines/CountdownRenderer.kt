package com.example.experiments.countdown.coroutines

import android.app.Activity
import android.util.Log
import com.lucasmontano.carreiranomundomobile.features.experiments.R
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentCountdownBinding
import kotlinx.coroutines.*

internal class CountdownRenderer(
  private val activity: Activity,
  private val binding: FragmentCountdownBinding,
  coroutineScope: CoroutineScope,
) {

  private val scope = CoroutineScope(Job() + Dispatchers.Main)

  private var countdownValue = 60

  init {
    coroutineScope.launch {
      Log.d(this::class.java.name, "Launching Coroutines")
      while (countdownValue > 0) {
        countdownValue--
        val countdownLabel = activity.getString(R.string.experiment_seconds_label, countdownValue)
        binding.experimentTextView.text = countdownLabel
        Log.d(this::class.java.name, "countdownLabel: $countdownLabel")
        delay(1000)
      }
    }
  }

  fun tearDown() {

  }
}
