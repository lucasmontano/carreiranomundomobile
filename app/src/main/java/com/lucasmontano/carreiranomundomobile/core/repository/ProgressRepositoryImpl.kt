package com.lucasmontano.carreiranomundomobile.core.repository

import android.util.Log
import com.lucasmontano.carreiranomundomobile.core.model.ProgressDomain
import java.util.*

object ProgressRepositoryImpl : ProgressRepository {

  private val progressListCache: MutableList<ProgressDomain> = mutableListOf()

  override suspend fun fetch(habitId: String, completedAt: Long): List<ProgressDomain> {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = completedAt
    return progressListCache.filter {
      it.habitId == habitId && it.dayOfWeek == calendar.get(Calendar.DAY_OF_WEEK)
    }
  }

  override suspend fun delete(id: String) {
    Log.d(LOG_TAG, "Losing progress on habitId: $id")
    progressListCache.removeAll { it.id == id }
  }

  override suspend fun add(habitId: String) {
    val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    val progress = ProgressDomain(
      id = UUID.randomUUID().toString(),
      habitId = habitId,
      dayOfWeek = dayOfWeek
    )

    Log.d(LOG_TAG, "Making progress on $dayOfWeek for habitId: $habitId")

    progressListCache.add(progress)
  }

  private const val LOG_TAG = "HabitProgressRepository"
}
