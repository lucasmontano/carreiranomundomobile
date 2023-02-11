package com.lucasmontano.carreiranomundomobile.core.di

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
  abstract fun providesHabitRepository(impl: com.lucasmontano.carreiranomundomobile.core.repository.HabitRepositoryImpl): com.lucasmontano.carreiranomundomobile.core.repository.HabitRepository

  @Singleton
  @Binds
  abstract fun providesProgressRepository(impl: com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepositoryImpl): com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepository
}
