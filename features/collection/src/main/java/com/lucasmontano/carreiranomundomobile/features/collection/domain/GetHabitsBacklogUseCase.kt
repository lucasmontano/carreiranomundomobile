package com.lucasmontano.carreiranomundomobile.collections.domain

import com.lucasmontano.carreiranomundomobile.collections.model.HabitBacklogItem

interface GetHabitsBacklogUseCase {

  suspend operator fun invoke(): List<HabitBacklogItem>
}