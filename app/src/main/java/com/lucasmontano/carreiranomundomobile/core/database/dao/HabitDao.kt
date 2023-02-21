package com.lucasmontano.carreiranomundomobile.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lucasmontano.carreiranomundomobile.core.database.entity.Habit

@Dao
interface HabitDao {

  @Query("SELECT * FROM habit WHERE daysOfWeek LIKE '%'||:dayOfWeek||'%'")
  suspend fun fetchByDayOfWeek(dayOfWeek: Int): List<Habit>

  @Query("SELECT * FROM habit")
  suspend fun fetchAll(): List<Habit>

  @Query("""
    SELECT * FROM habit
    WHERE uuid LIKE :habitId
  """)
  suspend fun fetchHabitById(habitId: String): Habit

  @Insert
  suspend fun insert(habit: Habit)
}
