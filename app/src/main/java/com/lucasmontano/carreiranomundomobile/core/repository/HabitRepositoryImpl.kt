package com.lucasmontano.carreiranomundomobile.core.repository

import android.util.Log
import com.lucasmontano.carreiranomundomobile.core.database.AppDatabase
import com.lucasmontano.carreiranomundomobile.core.database.entity.Habit
import com.lucasmontano.carreiranomundomobile.core.model.HabitDomain
import java.util.*

class HabitRepositoryImpl(appDatabase: AppDatabase) : HabitRepository {

  private val dao = appDatabase.habitDao()

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
