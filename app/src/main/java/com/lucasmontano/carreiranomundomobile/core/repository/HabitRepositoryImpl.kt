package com.lucasmontano.carreiranomundomobile.core.repository

import android.util.Log
import com.lucasmontano.carreiranomundomobile.core.model.HabitDomain
import java.util.*

object HabitRepositoryImpl : HabitRepository {

  private val habitListCache: MutableList<HabitDomain> = mutableListOf()

  override suspend fun fetchAll() = habitListCache

  override suspend fun fetch(dayOfWeek: Int): List<HabitDomain> {
    return habitListCache.filter {
      it.daysOfWeek.contains(dayOfWeek)
    }
  }

  override suspend fun add(title: String, daysOfWeek: List<Int>) {
    val habit = HabitDomain(
      id = UUID.randomUUID().toString(),
      title = title,
      daysOfWeek = daysOfWeek
    )

    Log.d(TAG, "Adding new Habit $title for days $daysOfWeek")

    habitListCache.add(habit)
  }

  private const val TAG = "HabitRepository"
}
