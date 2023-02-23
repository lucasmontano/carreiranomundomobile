package com.lucasmontano.carreiranomundomobile.features.collection.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lucasmontano.carreiranomundomobile.core.model.ProgressDomain
import com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepository
import com.lucasmontano.carreiranomundomobile.features.collection.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ToggleProgressUseCaseTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  val testCoroutineRule = TestCoroutineRule()

  private val progressRepository: ProgressRepository = mock()

  private val toggleProgressUseCase =
    ToggleProgressUseCaseImpl(
      progressRepository
    )

  @Test
  fun `when invoke is called and progress list is not empty then delete progress`() {
    testCoroutineRule.runBlockingTest {
      // given
      val mockProgress = listOf(
        ProgressDomain(
          "1",
          "habitId",
          1
        )
      )
      val habitId = "habitId"

      whenever(progressRepository.fetch(any(), any())).thenReturn(mockProgress)

      // when
      toggleProgressUseCase.invoke(habitId)

      // then
      verify(progressRepository).delete("1")
      verify(progressRepository, never()).add(habitId)
    }
  }

  @Test
  fun `when invoke is called and progress list is empty then add progress`() {
    testCoroutineRule.runBlockingTest {
      // given
      val habitId = "habitId"
      whenever(progressRepository.fetch(any(), any())).thenReturn(emptyList())

      // when
      toggleProgressUseCase.invoke(habitId)

      // then
      verify(progressRepository, never()).delete("1")
      verify(progressRepository).add(habitId)
    }
  }
}
