package com.lucasmontano.carreiranomundomobile.features.collection.domain

import com.lucasmontano.carreiranomundomobile.features.collection.model.HabitItem

interface GetHabitsForTodayUseCase {

  suspend operator fun invoke(): List<HabitItem>
}
