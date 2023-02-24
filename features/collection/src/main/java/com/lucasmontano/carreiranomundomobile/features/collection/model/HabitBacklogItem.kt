package com.lucasmontano.carreiranomundomobile.collections.model

data class HabitBacklogItem(
  val id: String,
  val title: String,
  val daysOfWeek: List<Int>
)
