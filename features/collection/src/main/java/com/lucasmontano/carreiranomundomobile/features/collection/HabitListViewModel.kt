package com.lucasmontano.carreiranomundomobile.features.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasmontano.carreiranomundomobile.features.collection.domain.GetHabitsForTodayUseCase
import com.lucasmontano.carreiranomundomobile.features.collection.domain.ToggleProgressUseCase
import com.lucasmontano.carreiranomundomobile.features.collection.model.HabitItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @see [https://developer.android.com/topic/libraries/architecture/viewmodel]
 */
@HiltViewModel
class HabitListViewModel @Inject constructor(
  private val getHabitsForTodayUseCase: GetHabitsForTodayUseCase,
  private val toggleProgressUseCase: ToggleProgressUseCase,
) : ViewModel() {

  /**
   * Mutable Live Data that initialize with the current list of saved Habits.
   */
  private val uiState: MutableLiveData<UiState> by lazy {
    MutableLiveData<UiState>(UiState(habitItemList = emptyList()))
  }

  /**
   * Refresh UI State whenever View Resumes.
   */
  fun onResume() {
    viewModelScope.launch {
      refreshHabitList()
    }
  }

  /**
   * Expose the uiState as LiveData to UI.
   */
  fun stateOnceAndStream(): LiveData<UiState> {
    return uiState
  }

  /**
   * Toggle a Habit complete status.
   */
  fun toggleHabitCompleted(habitId: String) {
    viewModelScope.launch {
      toggleProgressUseCase(habitId)
      refreshHabitList()
    }
  }

  private suspend fun refreshHabitList() {
    uiState.postValue(UiState(getHabitsForTodayUseCase()))
  }

  /**
   * UI State containing every data needed to show Habits.
   */
  data class UiState(val habitItemList: List<HabitItem>)
}
