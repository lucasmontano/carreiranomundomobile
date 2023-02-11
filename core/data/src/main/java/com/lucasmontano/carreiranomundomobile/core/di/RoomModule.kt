package com.lucasmontano.carreiranomundomobile.core.di

import android.app.Application
import com.lucasmontano.carreiranomundomobile.core.database.AppDatabase
import com.lucasmontano.carreiranomundomobile.core.database.dao.HabitDao
import com.lucasmontano.carreiranomundomobile.core.database.dao.ProgressDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

  @Singleton
  @Provides
  fun providesRoomDatabase(application: Application): com.lucasmontano.carreiranomundomobile.core.database.AppDatabase {
    return com.lucasmontano.carreiranomundomobile.core.database.AppDatabase.getInstance(application)
  }

  @Singleton
  @Provides
  fun providesHabitDao(database: com.lucasmontano.carreiranomundomobile.core.database.AppDatabase): com.lucasmontano.carreiranomundomobile.core.database.dao.HabitDao {
    return database.habitDao()
  }

  @Singleton
  @Provides
  fun providesProgressDao(database: com.lucasmontano.carreiranomundomobile.core.database.AppDatabase): com.lucasmontano.carreiranomundomobile.core.database.dao.ProgressDao {
    return database.progressDao()
  }
}
