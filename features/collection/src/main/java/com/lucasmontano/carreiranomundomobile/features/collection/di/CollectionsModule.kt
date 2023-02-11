package com.lucasmontano.carreiranomundomobile.features.collection.di

import com.lucasmontano.carreiranomundomobile.features.collection.domain.GetHabitsForTodayUseCase
import com.lucasmontano.carreiranomundomobile.features.collection.domain.GetHabitsForTodayUseCaseImpl
import com.lucasmontano.carreiranomundomobile.features.collection.domain.ToggleProgressUseCase
import com.lucasmontano.carreiranomundomobile.features.collection.domain.ToggleProgressUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class CollectionsModule {

  @Singleton
  @Binds
  abstract fun providesGetHabitsForTodayUseCase(
    impl: GetHabitsForTodayUseCaseImpl
  ): GetHabitsForTodayUseCase

  @Singleton
  @Binds
  abstract fun providesToggleProgressUseCase(
    impl: ToggleProgressUseCaseImpl
  ): ToggleProgressUseCase
}
