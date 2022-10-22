package com.lucasmontano.carreiranomundomobile.core

import com.lucasmontano.carreiranomundomobile.collections.HabitItem

interface HabitsRepository {

  fun fetchHabits(): List<HabitItem>

  fun toggleHabitCompleted(id: String)

  fun addHabit(name: String, weekDays: List<Int>)
}
