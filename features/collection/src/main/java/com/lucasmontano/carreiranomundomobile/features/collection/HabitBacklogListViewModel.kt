package com.lucasmontano.carreiranomundomobile.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasmontano.carreiranomundomobile.collections.domain.GetHabitsBacklogUseCase
import com.lucasmontano.carreiranomundomobile.collections.model.HabitBacklogItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitBacklogListViewModel @Inject constructor(
  private val getHabitsBacklogUseCase: GetHabitsBacklogUseCase,
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

  private suspend fun refreshHabitList() {
    uiState.postValue(UiState(getHabitsBacklogUseCase()))
  }

  fun onBacklogItemClick(itemIndex: Int): String {
    val habit: HabitBacklogItem = uiState.value!!.habitItemList[itemIndex]
    return habit.id
  }

  /**
   * UI State containing every data needed to show Habits.
   */
  data class UiState(val habitItemList: List<HabitBacklogItem>)
}
