package com.example.experiments.gpt

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAIApi {

  @POST("v1/engines/davinci/completions")
  suspend fun getHabitTips(@Body prompt: OpenAIPrompt): Response<OpenAIResponse>
}

data class OpenAIPrompt(
  val prompt: String,
  val max_tokens: Int = 50,
  val n: Int = 1,
  val temperature: Double = 1.0
)

data class OpenAIResponse(val choices: List<OpenAIChoice>)
data class OpenAIChoice(val text: String)
