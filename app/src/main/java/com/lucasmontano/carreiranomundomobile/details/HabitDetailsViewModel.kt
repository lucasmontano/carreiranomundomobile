package com.lucasmontano.carreiranomundomobile.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lucasmontano.carreiranomundomobile.core.model.HabitHistoryDomain
import com.lucasmontano.carreiranomundomobile.details.domain.GetHabitHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HabitDetailsViewModel @Inject constructor(
    private val getHabitHistoryUseCase: GetHabitHistoryUseCase,
    private val state: SavedStateHandle
) : ViewModel() {

    suspend fun getHabitDetails(): HabitHistoryDomain {
        val habitId = state.get<String>("habitUUID")!!
        return getHabitHistoryUseCase.invoke(habitId)
    }
}