package com.lucasmontano.carreiranomundomobile.collections

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lucasmontano.carreiranomundomobile.collections.domain.GetHabitsForTodayUseCase
import com.lucasmontano.carreiranomundomobile.collections.domain.ToggleProgressUseCase
import com.lucasmontano.carreiranomundomobile.collections.model.HabitItem
import com.lucasmontano.carreiranomundomobile.utils.TestCoroutineRule
import com.lucasmontano.carreiranomundomobile.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HabitListViewModelTest {

  /**
   * InstantTaskExecutorRule swaps the background executor used by the Architecture Components
   * with a different one which executes each task synchronously.
   */
  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  val testCoroutineRule = TestCoroutineRule()

  private val toggleProgressUseCase = mock<ToggleProgressUseCase>()
  private val getHabitsForTodayUseCase = mock<GetHabitsForTodayUseCase>()

  private val viewModel = HabitListViewModel(
    toggleProgressUseCase = toggleProgressUseCase,
    getHabitsForTodayUseCase = getHabitsForTodayUseCase
  )

  @Test
  fun `Verify uiState is initialized with Habits`() {
    testCoroutineRule.runBlockingTest {
      // Prepare
      whenever(getHabitsForTodayUseCase.invoke()).thenReturn(
        listOf(
          HabitItem(id = "ID", title = "title", isCompleted = false)
        )
      )
      viewModel.onResume()

      // Execute
      val uiState = viewModel.stateOnceAndStream().getOrAwaitValue()

      // Verify
      assert(uiState.habitItemList.isNotEmpty()) // verify uiState has items when initialized
    }
  }

  @Test
  fun `Verify toggleProgressUseCase is invoked when toggleHabitCompleted is called`() {
    testCoroutineRule.runBlockingTest {
      // Prepare
      whenever(toggleProgressUseCase.invoke("ID")).thenReturn(Unit)
      viewModel.onResume()

      // Execute
      viewModel.toggleHabitCompleted("ID")

      // Verify
      verify(toggleProgressUseCase).invoke("ID")
    }
  }
}
