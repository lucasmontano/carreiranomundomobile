package com.lucasmontano.carreiranomundomobile.di

import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepository
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepositoryImpl
import com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepository
import com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

  @Singleton
  @Binds
  abstract fun providesHabitRepository(impl: HabitRepositoryImpl): HabitRepository

  @Singleton
  @Binds
  abstract fun providesProgressRepository(impl: ProgressRepositoryImpl): ProgressRepository
}
