package com.example.experiments.quotes.threads

import android.app.Activity
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentQuotesBinding

internal class ThreadsQuotesRenderer(
  activity: Activity,
  binding: FragmentQuotesBinding,
  threadsQuotesRepository: ThreadsQuotesRepository
) {

  private var apiRequestThread: Thread = Thread { // Evitando travar a MainThread
    val quote = threadsQuotesRepository.getOne()
    // Evitando causar um erro de atualizar UI fora da MainThread
    activity.runOnUiThread {
      binding.contentLoadingProgressBar.hide()
      binding.experimentTextView.text = quote.quote
    }
  }

  init {
    binding.contentLoadingProgressBar.show()
    apiRequestThread.start()
  }

  fun tearDown() {
    apiRequestThread.interrupt()
  }
}