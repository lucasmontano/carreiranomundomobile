package com.lucasmontano.carreiranomundomobile.collections.domain

import com.lucasmontano.carreiranomundomobile.collections.model.HabitItem

interface GetHabitsForTodayUseCase {

  suspend operator fun invoke(): List<HabitItem>
}
