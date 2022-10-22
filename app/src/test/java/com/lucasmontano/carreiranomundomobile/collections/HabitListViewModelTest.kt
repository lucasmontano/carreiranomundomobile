package com.lucasmontano.carreiranomundomobile.collections

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HabitListViewModelTest {

  /**
   * InstantTaskExecutorRule swaps the background executor used by the Architecture Components
   * with a different one which executes each task synchronously.
   */
  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  private val testHabitRepository = TestHabitRepository()

  private val viewModel = HabitListViewModel(repository = testHabitRepository)

  @Before
  fun setup() {
    testHabitRepository.habitList.clear()
  }

  @Test
  fun `Verify uiState is initialized with Habits`() {
    // Prepare
    testHabitRepository.habitList.add(
      HabitItem(
        id = "ID", title = "Test Habit", isCompleted = false
      )
    )

    // Execute
    val uiState = viewModel.stateOnceAndStream().getOrAwaitValue()

    // Verify
    assert(uiState.habitItemList.isNotEmpty()) // verify uiState has items when initialized
  }

  @Test
  fun `Verify uiState is updated when new Habit is added`() {
    // Prepare
    testHabitRepository.habitList.add(
      HabitItem(
        id = "ID", title = "Test Habit", isCompleted = false
      )
    )

    // Execute
    val uiStateInit = viewModel.stateOnceAndStream().getOrAwaitValue()
    val initialHabitListSize = uiStateInit.habitItemList.size

    viewModel.addHabit("Test Habit", emptyList()) // Add new Habit

    val updatedUiState = viewModel.stateOnceAndStream().getOrAwaitValue()
    val currentSize = updatedUiState.habitItemList.size
    val expectedSize = initialHabitListSize + 1 // Expected size be initial + 1

    // Verify
    assert(currentSize == expectedSize)
  }

  @Test
  fun `Verify uiState is updated when new Habit is completed`() {
    // Observe Ui State
    // Check a habit as completed
    // Get updated UiState
    // Verify the habit is now completed
  }
}
