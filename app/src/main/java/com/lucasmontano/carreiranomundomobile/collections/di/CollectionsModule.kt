package com.lucasmontano.carreiranomundomobile.collections.di

import com.lucasmontano.carreiranomundomobile.collections.domain.GetHabitsForTodayUseCase
import com.lucasmontano.carreiranomundomobile.collections.domain.GetHabitsForTodayUseCaseImpl
import com.lucasmontano.carreiranomundomobile.collections.domain.ToggleProgressUseCase
import com.lucasmontano.carreiranomundomobile.collections.domain.ToggleProgressUseCaseImpl
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepository
import com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CollectionsModule {

  @Singleton
  @Provides
  fun providesGetHabitsForTodayUseCase(
    progressRepository: ProgressRepository,
    habitRepository: HabitRepository,
  ): GetHabitsForTodayUseCase {
    return GetHabitsForTodayUseCaseImpl(
      progressRepository = progressRepository,
      habitRepository = habitRepository
    )
  }

  @Singleton
  @Provides
  fun providesToggleProgressUseCase(
    progressRepository: ProgressRepository
  ): ToggleProgressUseCase {
    return ToggleProgressUseCaseImpl(
      progressRepository = progressRepository
    )
  }
}
