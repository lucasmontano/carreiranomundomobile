package com.lucasmontano.carreiranomundomobile.features.collection.model

/**
 * Habit Model representing an Item in a ListView.
 *
 * @param id the id of the Habit
 * @param title the title of the habit
 * @param isCompleted TRUE whether the habit has a progress history
 */
data class HabitItem(
  val id: String,
  val title: String,
  val isCompleted: Boolean
)
