package com.lucasmontano.carreiranomundomobile.dummy

import com.lucasmontano.carreiranomundomobile.collections.HabitItem
import com.lucasmontano.carreiranomundomobile.core.HabitsRepository
import java.util.*

/**
 * Mock data with [HabitItem] for the collection.
 */
object MockHabits : HabitsRepository {

  private val habitItemList: MutableList<HabitItem> = mutableListOf()

  override fun fetchHabits() = habitItemList.map { it.copy() }

  override fun addHabit(name: String, weekDays: List<Int>) {
    habitItemList.add(
      HabitItem(
        id = UUID.randomUUID().toString(), title = name, isCompleted = false
      )
    )
  }

  override fun toggleHabitCompleted(id: String) {
    val habitIndex = findHabitIndexById(id)
    val habit = habitItemList[habitIndex]
    habitItemList[habitIndex] = habit.copy(isCompleted = !habit.isCompleted)
  }

  private fun findHabitIndexById(id: String) = habitItemList.indexOfFirst { habitItem ->
    habitItem.id == id
  }
}
