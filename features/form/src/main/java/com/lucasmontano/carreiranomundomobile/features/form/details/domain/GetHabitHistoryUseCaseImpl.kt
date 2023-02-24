package com.lucasmontano.carreiranomundomobile.features.form.details.domain

import android.util.Log
import com.lucasmontano.carreiranomundomobile.core.model.HabitHistoryDomain
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepository
import com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepository
import javax.inject.Inject

class GetHabitHistoryUseCaseImpl @Inject constructor(
  private val habitRepository: HabitRepository,
  private val progressRepository: ProgressRepository
) : GetHabitHistoryUseCase {
  override suspend fun invoke(uuid: String): HabitHistoryDomain {
    Log.d(TAG, "Fetching the history for the habit $uuid")

    val habit = habitRepository.fetchHabitById(uuid)
    val history = progressRepository.fetchHabitHistory(uuid)
    return HabitHistoryDomain(habit, history)
  }

  companion object {
    private const val TAG = "GetHabitHistoryUseCase"
  }
}