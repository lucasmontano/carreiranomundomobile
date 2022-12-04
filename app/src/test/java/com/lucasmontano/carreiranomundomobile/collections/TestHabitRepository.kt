package com.lucasmontano.carreiranomundomobile.collections

import com.lucasmontano.carreiranomundomobile.core.HabitsRepository
import java.util.*

/**
 * Simple Repository to be used for test purpose.
 */
class TestHabitRepository : HabitsRepository {

  val habitList = mutableListOf<HabitItem>()

  override fun fetchHabits() = habitList

  override fun addHabit(name: String,  weekDays: List<Int>) {
    habitList.add(
      HabitItem(id = UUID.randomUUID().toString(), title = name, isCompleted = false)
    )
  }

  override fun toggleHabitCompleted(id: String) {
    val index = habitList.indexOfFirst { it.id == id }
    habitList[index] = habitList[index].copy(isCompleted = !habitList[index].isCompleted)
  }
}
