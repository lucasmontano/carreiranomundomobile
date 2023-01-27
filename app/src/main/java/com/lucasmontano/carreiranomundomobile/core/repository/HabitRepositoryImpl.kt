package com.lucasmontano.carreiranomundomobile.core.repository

import android.util.Log
import com.lucasmontano.carreiranomundomobile.core.database.dao.HabitDao
import com.lucasmontano.carreiranomundomobile.core.database.entity.Habit
import com.lucasmontano.carreiranomundomobile.core.model.HabitDomain
import java.util.*
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(private val dao: HabitDao) : HabitRepository {

  override suspend fun fetch(dayOfWeek: Int): List<HabitDomain> {
    Log.d(TAG, "Fetching habits for day of week $dayOfWeek")
    return dao.fetchByDayOfWeek(dayOfWeek).map {
      HabitDomain(
        id = it.uuid,
        title = it.title,
        daysOfWeek = it.daysOfWeek
      )
    }
  }

  override suspend fun add(title: String, daysOfWeek: List<Int>) {
    Log.d(TAG, "Adding new Habit $title for days $daysOfWeek")
    val habit = Habit(
      uuid = UUID.randomUUID().toString(),
      title = title,
      daysOfWeek = daysOfWeek
    )
    dao.insert(habit)
  }

  companion object {
    private const val TAG = "HabitRepository"
  }
}
