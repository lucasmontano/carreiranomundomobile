package com.lucasmontano.carreiranomundomobile.features.collection.domain

import com.lucasmontano.carreiranomundomobile.collections.model.HabitBacklogItem

interface GetHabitsBacklogUseCase {

  suspend operator fun invoke(): List<HabitBacklogItem>
}