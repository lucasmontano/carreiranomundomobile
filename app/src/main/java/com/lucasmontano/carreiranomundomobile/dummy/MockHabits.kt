package com.lucasmontano.carreiranomundomobile.dummy

import com.lucasmontano.carreiranomundomobile.collections.HabitItem
import com.lucasmontano.carreiranomundomobile.core.HabitsRepository
import java.util.*

/**
 * Mock data with [HabitItem] for the collection.
 */
object MockHabits : HabitsRepository {

  private val randomHabitList = listOf(
    HabitItem(
      id = UUID.randomUUID().toString(),
      title = "Walk the dog",
      isCompleted = false
    ),
    HabitItem(
      id = UUID.randomUUID().toString(),
      title = "Do the dishes",
      isCompleted = false
    ),
    HabitItem(
      id = UUID.randomUUID().toString(),
      title = "Go to the gym",
      isCompleted = false
    ),
    HabitItem(
      id = UUID.randomUUID().toString(),
      title = "Code every day",
      isCompleted = false
    ),
    HabitItem(
      id = UUID.randomUUID().toString(),
      title = "Make a cup of tea",
      isCompleted = false
    ),
    HabitItem(
      id = UUID.randomUUID().toString(),
      title = "Make a cup of coffee",
      isCompleted = false
    )
  )

  private val habitItemList: MutableList<HabitItem> = mutableListOf(
    HabitItem(
      id = UUID.randomUUID().toString(),
      title = "Read the book",
      isCompleted = false
    )
  )

  override fun fetchHabits() = habitItemList.map { it.copy() }

  override fun addRandomNewHabit() {
    habitItemList.add(randomHabit())
  }

  override fun toggleHabitCompleted(id: String) {
    val habitIndex = findHabitIndexById(id)
    val habit = habitItemList[habitIndex]
    habitItemList[habitIndex] = habit.copy(isCompleted = !habit.isCompleted)
  }

  private fun randomHabit() = randomHabitList.random().copy(
    id = UUID.randomUUID().toString()
  )

  private fun findHabitIndexById(id: String) = habitItemList.indexOfFirst { habitItem ->
    habitItem.id == id
  }
}
