package com.example.experiments.countdown.rxjava

import android.app.Activity
import android.util.Log
import com.lucasmontano.carreiranomundomobile.features.experiments.R
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentCountdownBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

internal class RxJavaCountdownRenderer(
  activity: Activity,
  binding: FragmentCountdownBinding
) {

  private var disposable: Disposable

  private val countdownObservable = Observable
    .interval(1, TimeUnit.SECONDS)
    .takeWhile { it <= MAX_COUNTDOWN }
    .doOnNext { Log.d(this::class.java.name, "Interval: $it") }
    .subscribeOn(Schedulers.computation())
    .observeOn(AndroidSchedulers.mainThread())

  init {
    disposable = countdownObservable
      .map { MAX_COUNTDOWN - it }
      .map { activity.getString(R.string.experiment_seconds_label, it) }
      .subscribe(
        { countdownLabel ->
          binding.experimentTextView.text = countdownLabel
        },
        { error ->
          Log.d(this::class.java.name, "Fail to Count: $error")
        }
      )
  }

  fun tearDown() {
    disposable.dispose()
  }

  companion object {

    private const val MAX_COUNTDOWN = 60
  }
}
