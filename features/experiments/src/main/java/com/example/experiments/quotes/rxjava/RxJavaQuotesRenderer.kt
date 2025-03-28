package com.example.experiments.quotes.rxjava

import android.util.Log
import com.example.experiments.quotes.Quote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lucasmontano.carreiranomundomobile.features.experiments.BuildConfig
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentQuotesBinding
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.reflect.Type

internal class RxJavaQuotesRenderer(
  private val ioScheduler: Scheduler,
  private val androidScheduler: Scheduler
) {

  private val disposables = CompositeDisposable()

  fun bind(binding: FragmentQuotesBinding) {
    val disposable = quoteMaybe()
      .subscribeOn(ioScheduler) // Scheduler where this stream will run (IO Thread)
      .observeOn(androidScheduler) // Scheduler where the Observer will Observe (MainThread)
      .doOnSubscribe {
        // This will run when Observer subscribes to this Stream
        binding.contentLoadingProgressBar.show()
      }
      .subscribe( // providing a Observer (2 lambdas functions for success and error)
        { quote ->
          binding.experimentTextView.text = quote.quote
          binding.contentLoadingProgressBar.hide()
        },
        { error ->
          binding.experimentTextView.text = error.toString()
          binding.contentLoadingProgressBar.hide()
          Log.d(this::class.java.name, "Fail to Quote: $error")
        }
      )
    disposables.add(disposable)
  }

  fun tearDown() {
    // Dispose and Clear disposables
    disposables.dispose()
  }

  /**
   * This will return an Maybe Stream containing that will emit [Quote].
   */
  private fun quoteMaybe() = Single.fromCallable {
    apiRequest() // Creating an Single emitting the return of this function
  }
    .filter { it.isSuccessful } // Filter out only Successful responses (Single turns to Maybe)
    .map { response ->
      // mapping Response to [Quote]
      val json = response.body?.string()
      val listType: Type = object : TypeToken<List<Quote>>() {}.type
      Gson().fromJson<List<Quote>>(json, listType).first()
    }

  /**
   * OkHttp implementation detail to request an API.
   * This will run Sync.
   */
  private fun apiRequest(): Response {
    val client = OkHttpClient()
    val request = Request.Builder()
      .url(QUOTES_URL)
      .get()
      .addHeader("X-Api-Key", BuildConfig.API_NINJA_KEY)
      .build()
    return client.newCall(request).execute()
  }

  companion object {

    private const val QUOTES_URL = "https://api.api-ninjas.com/v1/quotes?category=success"
  }
}