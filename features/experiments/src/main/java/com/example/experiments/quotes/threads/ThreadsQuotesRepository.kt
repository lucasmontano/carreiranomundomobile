package com.example.experiments.quotes.threads

import com.example.experiments.quotes.Quote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lucasmontano.carreiranomundomobile.features.experiments.BuildConfig
import dagger.hilt.android.scopes.FragmentScoped
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Type
import javax.inject.Inject

@FragmentScoped
internal class ThreadsQuotesRepository @Inject constructor() {

  private val cachedQuotes = mutableListOf<Quote>()

  init {
    Thread {
      val client = OkHttpClient()
      val request = Request.Builder()
        .url(QUOTES_URL)
        .get()
        .addHeader("X-Api-Key", BuildConfig.API_NINJA_KEY)
        .build()

      // Dispatcher.IO: este agente é otimizado para executar E/S de disco ou rede
      val response = client.newCall(request).execute()

      if (response.isSuccessful) {
        val json = response.body?.string()
        val listType: Type = object : TypeToken<List<Quote>>() {}.type
        Gson().fromJson<List<Quote>>(json, listType).forEach {
          cachedQuotes.add(it)
        }
      }
    }.start()
  }

  fun getOne(): Quote {
    // Dispatcher.Default
    while (cachedQuotes.isEmpty()) {
      Thread.sleep(1000)
    }
    return cachedQuotes.random()
  }

  companion object {

    private const val QUOTES_URL = "https://api.api-ninjas.com/v1/quotes?category=success&limit=10"
  }
}
