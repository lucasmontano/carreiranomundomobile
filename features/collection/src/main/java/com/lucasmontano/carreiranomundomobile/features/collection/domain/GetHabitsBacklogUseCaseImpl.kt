package com.lucasmontano.carreiranomundomobile.features.collection.domain

import android.util.Log
import com.lucasmontano.carreiranomundomobile.collections.model.HabitBacklogItem
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepository
import javax.inject.Inject

class GetHabitsBacklogUseCaseImpl @Inject constructor(
  private val habitRepository: HabitRepository,
) : GetHabitsBacklogUseCase {
  override suspend fun invoke(): List<HabitBacklogItem> {

    Log.d(TAG, "Fetching all habits")

    return habitRepository
      .fetchAll()
      .map { habit ->
        HabitBacklogItem(
          id = habit.id,
          title = habit.title,
          daysOfWeek = habit.daysOfWeek
        )
      }
  }

  companion object {
    private const val TAG = "GetHabitsBacklog"
  }
}