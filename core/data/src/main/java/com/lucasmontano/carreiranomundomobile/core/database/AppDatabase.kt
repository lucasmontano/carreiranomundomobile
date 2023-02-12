package com.lucasmontano.carreiranomundomobile.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lucasmontano.carreiranomundomobile.core.database.dao.HabitDao
import com.lucasmontano.carreiranomundomobile.core.database.dao.ProgressDao
import com.lucasmontano.carreiranomundomobile.core.database.entity.Habit
import com.lucasmontano.carreiranomundomobile.core.database.entity.Progress

@Database(entities = [Habit::class, Progress::class], version = 1, exportSchema = false)
@TypeConverters(DaysOfWeekConverter::class)
internal abstract class AppDatabase : RoomDatabase() {

  abstract fun progressDao(): ProgressDao

  abstract fun habitDao(): HabitDao

  companion object {

    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
      if (instance == null) {
        synchronized(AppDatabase::class) {
          instance = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, DATABASE_NAME
          )
            .build()
        }
      }
      return instance!!
    }

    private const val DATABASE_NAME = "app-database.db"
  }
}
