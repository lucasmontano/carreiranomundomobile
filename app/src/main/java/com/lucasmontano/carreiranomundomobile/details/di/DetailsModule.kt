package com.lucasmontano.carreiranomundomobile.details.di

import com.lucasmontano.carreiranomundomobile.details.domain.GetHabitHistoryUseCase
import com.lucasmontano.carreiranomundomobile.details.domain.GetHabitHistoryUseCaseImpl
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