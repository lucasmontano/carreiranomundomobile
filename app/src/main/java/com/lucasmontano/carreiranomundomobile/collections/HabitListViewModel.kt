package com.lucasmontano.carreiranomundomobile.collections

import androidx.lifecycle.*
import com.lucasmontano.carreiranomundomobile.collections.domain.GetHabitsForTodayUseCase
import com.lucasmontano.carreiranomundomobile.collections.domain.ToggleProgressUseCase
import kotlinx.coroutines.launch

/**
 * @see [https://developer.android.com/topic/libraries/architecture/viewmodel]
 */
class HabitListViewModel(
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

  /**
   * ViewModel Factory needed to provide Repository injection to ViewModel.
   */
  @Suppress("UNCHECKED_CAST")
  class Factory(
    private val toggleProgressUseCase: ToggleProgressUseCase,
    private val getHabitsForTodayUseCase: GetHabitsForTodayUseCase,
  ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return HabitListViewModel(getHabitsForTodayUseCase, toggleProgressUseCase) as T
    }
  }
}
