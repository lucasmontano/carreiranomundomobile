package com.lucasmontano.carreiranomundomobile.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucasmontano.carreiranomundomobile.dummy.MockHabits

/**
 * @see [https://developer.android.com/topic/libraries/architecture/viewmodel]
 */
class HabitListViewModel : ViewModel() {

  private val uiState: MutableLiveData<UiState> by lazy {
    MutableLiveData<UiState>(UiState(habitItemList = MockHabits.fetchHabits()))
  }

  fun stateOnceAndStream(): LiveData<UiState> {
    return uiState
  }

  fun toggleHabitCompleted(id: String) {
    MockHabits.toggleHabitCompleted(id)
    refreshHabitList()
  }

  fun addRandomHabit() {
    MockHabits.addRandomNewHabit()
    refreshHabitList()
  }

  private fun refreshHabitList() {
    uiState.value?.let { currentUiState ->
      uiState.value = currentUiState.copy(
        habitItemList = MockHabits.fetchHabits()
      )
    }
  }

  data class UiState(val habitItemList: List<HabitItem>)
}
