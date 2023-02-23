package com.lucasmontano.carreiranomundomobile.core.repository

import com.lucasmontano.carreiranomundomobile.core.model.HabitDomain

interface HabitRepository {

  suspend fun fetch(dayOfWeek: Int): List<HabitDomain>

  suspend fun fetchAll(): List<HabitDomain>

  suspend fun fetchHabitById(habitId: String): HabitDomain

  suspend fun add(title: String, daysOfWeek: List<Int>)
}
