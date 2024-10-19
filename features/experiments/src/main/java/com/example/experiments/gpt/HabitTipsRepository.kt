package com.example.experiments.gpt

import javax.inject.Inject

class HabitTipsRepository @Inject constructor(private val openAIApi: OpenAIApi) {

  suspend fun getHabitTips(habitTitle: String): Result<String> {

    val prompt = "Como começar o habito de ${habitTitle}?"

    return try {
      val response = openAIApi.getHabitTips(OpenAIPrompt(prompt))
      if (response.isSuccessful) {
        val formattedText = "${response.body()?.choices?.firstOrNull()?.text}"
        Result.success(formattedText)
      } else {
        Result.failure(Exception("Erro ao obter dicas para $habitTitle"))
      }
    } catch (e: Exception) {
      Result.failure(e)
    }
  }
}
