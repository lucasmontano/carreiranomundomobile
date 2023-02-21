package com.lucasmontano.carreiranomundomobile.collections.di

import com.lucasmontano.carreiranomundomobile.collections.domain.*
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

    @Singleton
    @Binds
    abstract fun providesGetHabitsBacklogUseCase(
        impl: GetHabitsBacklogUseCaseImpl
    ): GetHabitsBacklogUseCase
}
