package com.lucasmontano.carreiranomundomobile.features.collection.domain

import android.util.Log
import com.lucasmontano.carreiranomundomobile.features.collection.model.HabitItem
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepository
import com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepository
import java.util.*
import javax.inject.Inject

internal class GetHabitsForTodayUseCaseImpl @Inject constructor(
  private val progressRepository: ProgressRepository,
  private val habitRepository: HabitRepository,
) : GetHabitsForTodayUseCase {

  override suspend fun invoke(): List<HabitItem> {
    val today = Calendar.getInstance()
    val dayOfWeek = today.get(Calendar.DAY_OF_WEEK)

    Log.d(TAG, "Fetching all habits for $dayOfWeek")

    return habitRepository
      .fetch(dayOfWeek)
      .map { habit ->
        Log.d(TAG, "Check we have already work on ${habit.id} at ${today.timeInMillis}")

        val progress = progressRepository.fetch(habit.id, today.timeInMillis)
        val isCompletedToday = progress.isNotEmpty()

        Log.d(TAG, "Habit for today: ${habit.title} is completed: $isCompletedToday")

        HabitItem(
          id = habit.id,
          title = habit.title,
          isCompleted = isCompletedToday
        )
      }
  }

  companion object {

    private const val TAG = "GetHabitsForToday"
  }
}