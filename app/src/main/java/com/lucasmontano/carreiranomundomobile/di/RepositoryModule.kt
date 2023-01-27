package com.lucasmontano.carreiranomundomobile.di

import com.lucasmontano.carreiranomundomobile.core.database.dao.HabitDao
import com.lucasmontano.carreiranomundomobile.core.database.dao.ProgressDao
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepository
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepositoryImpl
import com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepository
import com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

  @Singleton
  @Provides
  fun providesHabitRepository(habitDao: HabitDao): HabitRepository {
    return HabitRepositoryImpl(habitDao)
  }

  @Singleton
  @Provides
  fun providesProgressRepository(progressDao: ProgressDao): ProgressRepository {
    return ProgressRepositoryImpl(progressDao)
  }
}
