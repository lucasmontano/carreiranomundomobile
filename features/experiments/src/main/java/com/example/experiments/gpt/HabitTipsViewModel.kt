package com.example.experiments.gpt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitTipsViewModel @Inject constructor(
  private val repository: HabitTipsRepository,
  private val habitRepository: HabitRepository
) : ViewModel() {

  val uiState: MutableLiveData<UiState> = MutableLiveData()

  fun loadHabitTip() {
    viewModelScope.launch {
      uiState.value = UiState.Loading

      try {
        val firstHabit = habitRepository.fetchAll().first()
        val result = repository.getHabitTips(firstHabit.title)
        result.fold(
          onSuccess = { tips ->
            uiState.value = UiState.Success(tips)
          },
          onFailure = { exception ->
            uiState.value = UiState.Error(exception)
          }
        )
      } catch (e: Exception) {
        uiState.value = UiState.Error(e)
      }
    }
  }

  sealed class UiState {

    object Loading : UiState()
    data class Success(val tips: String) : UiState()
    data class Error(val exception: Throwable) : UiState()
  }
}
