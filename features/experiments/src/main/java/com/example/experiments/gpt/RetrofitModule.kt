package com.example.experiments.gpt

import com.lucasmontano.carreiranomundomobile.features.experiments.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

  private const val BASE_URL = "https://api.openai.com/"

  @Provides
  @Singleton
  fun provideApiKeyInterceptor(): ApiKeyInterceptor {
    return ApiKeyInterceptor(BuildConfig.OPEN_AI_API_KEY)
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(apiKeyInterceptor)
      .build()
  }

  @Provides
  @Singleton
  fun provideOpenAIApi(okHttpClient: OkHttpClient): OpenAIApi {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(OpenAIApi::class.java)
  }
}

