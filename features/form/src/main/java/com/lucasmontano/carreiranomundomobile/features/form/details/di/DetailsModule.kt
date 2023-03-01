package com.lucasmontano.carreiranomundomobile.features.form.details.di

import com.lucasmontano.carreiranomundomobile.features.form.details.domain.GetHabitHistoryUseCase
import com.lucasmontano.carreiranomundomobile.features.form.details.domain.GetHabitHistoryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DetailsModule {

  @Singleton
  @Binds
  abstract fun providesGetHabitsHistoryUseCase(
    impl: GetHabitHistoryUseCaseImpl
  ): GetHabitHistoryUseCase
}