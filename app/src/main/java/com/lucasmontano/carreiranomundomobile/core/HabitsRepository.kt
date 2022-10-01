package com.lucasmontano.carreiranomundomobile.core

import com.lucasmontano.carreiranomundomobile.collections.HabitItem

interface HabitsRepository {

  fun fetchHabits(): List<HabitItem>

  fun addRandomNewHabit()

  fun toggleHabitCompleted(id: String)
}
