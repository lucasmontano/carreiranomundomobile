package com.lucasmontano.carreiranomundomobile.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lucasmontano.carreiranomundomobile.core.database.entity.Habit

@Dao
interface HabitDao {

  @Query("SELECT * FROM habit WHERE daysOfWeek LIKE '%'||:dayOfWeek||'%'")
  suspend fun fetchByDayOfWeek(dayOfWeek: Int): List<Habit>

  @Insert
  suspend fun insert(habit: Habit)
}
