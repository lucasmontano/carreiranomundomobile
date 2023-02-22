package com.example.experiments.quotes.coroutines

import com.example.experiments.quotes.Quote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lucasmontano.carreiranomundomobile.features.experiments.BuildConfig
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentQuotesBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Type

internal class QuotesRenderer(
  scope: CoroutineScope,
  ioDispatcher: CoroutineDispatcher,
  binding: FragmentQuotesBinding
) {

  init {
    binding.contentLoadingProgressBar.show()

    val client = OkHttpClient()
    val request = Request.Builder()
      .url(QUOTES_URL)
      .get()
      .addHeader("X-Api-Key", BuildConfig.MY_API_KEY)
      .build()

    // Dispatcher.IO: este agente é otimizado para executar E/S de disco ou rede
    scope.launch {
      val response = withContext(ioDispatcher) {
        client.newCall(request).execute()
      }
      if (response.isSuccessful) {
        val json = response.body?.string()
        val listType: Type = object : TypeToken<List<Quote>>() {}.type
        val quote = Gson().fromJson<List<Quote>>(json, listType).first()

        binding.contentLoadingProgressBar.hide()
        binding.experimentTextView.text = quote.quote
      } else {
        binding.contentLoadingProgressBar.hide()
        binding.experimentTextView.text = "Error: ${response.code}"
      }
    }
  }

  companion object {

    private const val QUOTES_URL = "https://api.api-ninjas.com/v1/quotes?category=success"
  }
}