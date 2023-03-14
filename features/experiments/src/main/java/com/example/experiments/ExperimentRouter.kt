package com.example.experiments

import androidx.fragment.app.FragmentActivity
import com.example.experiments.countdown.coroutines.CountDownFragment
import com.example.experiments.countdown.rxjava.RxJavaCountDownFragment
import com.example.experiments.countdown.threads.ThreadsCountDownFragment
import com.example.experiments.quotes.coroutines.QuotesFragment
import com.example.experiments.quotes.rxjava.RxJavaQuotesFragment
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
    val fragment = fragmentActivity.supportFragmentManager.findFragmentById(R.id.experimentsFrameLayout)
    if (fragment == null) {
      val experimentFragment = when (experiment) {
        is Experiment.CountDown.WithThread -> ThreadsCountDownFragment()
        is Experiment.CountDown.WithCoroutines -> CountDownFragment()
        is Experiment.CountDown.WithRxJava -> RxJavaCountDownFragment()
        is Experiment.Quotes.WithThread -> ThreadsQuotesFragment()
        is Experiment.Quotes.WithCoroutines -> QuotesFragment()
        is Experiment.Quotes.WithRxJava -> RxJavaQuotesFragment()
        is Experiment.Quotes.WithFlow -> {
          throw Exception()
        }
      }
      fragmentTransaction.add(R.id.experimentsFrameLayout, experimentFragment)
      fragmentTransaction.commit()
    }
  }

  sealed interface Experiment {

    sealed class CountDown {
      object WithThread : CountDown(), Experiment

      object WithCoroutines : CountDown(), Experiment

      object WithRxJava : CountDown(), Experiment
    }

    sealed class Quotes {
      object WithThread : Quotes(), Experiment

      object WithCoroutines : Quotes(), Experiment

      object WithFlow : Quotes(), Experiment

      object WithRxJava : Quotes(), Experiment
    }
  }
}
