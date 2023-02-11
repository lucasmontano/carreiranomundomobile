package com.lucasmontano.carreiranomundomobile.features.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitFormViewModel @Inject constructor(
  private val habitRepository: HabitRepository
) : ViewModel() {

  /**
   * Add new Random Habit.
   *
   * @param name: The name you wanna give to this Habit
   * @param habitDaysSelected: Which days do you wanna practice the Habit
   */
  fun addHabit(name: String, habitDaysSelected: List<Int>) {
    viewModelScope.launch {
      habitRepository.add(name, habitDaysSelected)
    }
  }
}
