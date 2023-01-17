package com.lucasmontano.carreiranomundomobile.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepository
import kotlinx.coroutines.launch

class HabitFormViewModel(
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

  /**
   * ViewModel Factory needed to provide Repository injection to ViewModel.
   */
  @Suppress("UNCHECKED_CAST")
  class Factory(
    private val habitRepository: HabitRepository,
  ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return HabitFormViewModel(habitRepository) as T
    }
  }
}
