package com.lucasmontano.carreiranomundomobile.collections.domain

import com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepository
import java.util.*

class ToggleProgressUseCaseImpl(
  private val progressRepository: ProgressRepository
) : ToggleProgressUseCase {

  override suspend fun invoke(habitId: String) {
    val today = Calendar.getInstance()
    val progress = progressRepository.fetch(habitId = habitId, today.timeInMillis)
    if (progress.isNotEmpty()) {
      progressRepository.delete(progress.first().id)
    } else {
      progressRepository.add(habitId = habitId)
    }
  }
}
