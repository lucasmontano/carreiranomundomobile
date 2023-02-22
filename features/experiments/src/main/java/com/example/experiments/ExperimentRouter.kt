package com.example.experiments

import androidx.fragment.app.FragmentActivity
import com.example.experiments.countdown.coroutines.CountDownFragment
import com.example.experiments.countdown.threads.ThreadsCountDownFragment
import com.example.experiments.quotes.coroutines.QuotesFragment
import com.example.experiments.quotes.coroutines.QuotesRenderer
import com.example.experiments.quotes.threads.ThreadsQuotesFragment
import com.lucasmontano.carreiranomundomobile.features.experiments.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ExperimentRouter @Inject constructor(
  private val fragmentActivity: FragmentActivity
) {

  fun runExperiment(experiment: Experiment) {
    val fragmentTransaction = fragmentActivity.supportFragmentManager.beginTransaction()
    val experimentFragment = when(experiment) {
      is Experiment.CountDown -> {
        if (experiment.useThreads) {
          ThreadsCountDownFragment()
        } else {
          CountDownFragment()
        }
      }
      is Experiment.Quotes -> {
        if (experiment.useThreads) {
          ThreadsQuotesFragment()
        } else {
          QuotesFragment()
        }
      }
    }
    fragmentTransaction.replace(R.id.experimentsFrameLayout, experimentFragment)
    fragmentTransaction.commit()
  }

  sealed class Experiment(open val useThreads: Boolean) {

    data class CountDown(override val useThreads: Boolean = false) : Experiment(useThreads)
    data class Quotes(override val useThreads: Boolean = false) : Experiment(useThreads)
  }
}
