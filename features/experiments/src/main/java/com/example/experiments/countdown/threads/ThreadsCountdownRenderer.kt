package com.example.experiments.countdown.threads

import android.app.Activity
import android.util.Log
import com.lucasmontano.carreiranomundomobile.features.experiments.R
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentCountdownBinding

internal class ThreadsCountdownRenderer(
  activity: Activity,
  binding: FragmentCountdownBinding
) {

  private var countdownThread: Thread
  private var countdownValue = 60

  init {
    // Dispatcher.Default: este agente é otimizado para realizar trabalho intensivo da CPU fora da linha de execução principal
    countdownThread = Thread {
      try {
        Log.d(this::class.java.name, "Creating Thread")
        while (countdownValue > 0 && !Thread.currentThread().isInterrupted) {
          countdownValue--
          val countdownLabel = activity.getString(R.string.experiment_seconds_label, countdownValue)
          binding.experimentTextView.text = countdownLabel
          Log.d(this::class.java.name, "countdownLabel: $countdownLabel")
          Thread.sleep(1000)
        }
      } catch (e: InterruptedException) {
        // A thread foi interrompida. Saia da thread.
        return@Thread
      }
    }
    countdownThread.start()
  }

  fun tearDown() {
    countdownThread.interrupt()
  }
}
