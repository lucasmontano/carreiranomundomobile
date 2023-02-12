package com.lucasmontano.carreiranomundomobile.core.repository

import android.util.Log
import com.lucasmontano.carreiranomundomobile.core.database.dao.ProgressDao
import com.lucasmontano.carreiranomundomobile.core.database.entity.Progress
import com.lucasmontano.carreiranomundomobile.core.model.ProgressDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

internal class ProgressRepositoryImpl @Inject constructor(
  private val dao: ProgressDao
) : ProgressRepository {

  override suspend fun fetch(habitId: String, completedAt: Long): List<ProgressDomain> {
    return dao.fetchProgressByHabit(habitId, completedAt).map { progress ->
      val calendar = Calendar.getInstance()
      calendar.timeInMillis = progress.completedAt
      ProgressDomain(
        id = progress.uuid,
        habitId = habitId,
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK),
      )
    }
  }

  override suspend fun delete(id: String) {
    Log.d(LOG_TAG, "Losing progress on habitId: $id")
    dao.delete(id)
  }

  override suspend fun add(habitId: String) {
    val today = Calendar.getInstance()
    val dayOfWeek = today.get(Calendar.DAY_OF_WEEK)

    Log.d(LOG_TAG, "Making progress on $dayOfWeek for habitId: $habitId")

    val progress = Progress(
      uuid = UUID.randomUUID().toString(),
      habitId = habitId,
      completedAt = today.timeInMillis,
    )
    withContext(Dispatchers.IO) {
      dao.insert(progress)
    }
  }

  companion object {
    private const val LOG_TAG = "HabitProgressRepository"
  }
}
