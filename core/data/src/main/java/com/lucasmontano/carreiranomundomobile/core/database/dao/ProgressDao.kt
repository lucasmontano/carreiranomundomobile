package com.lucasmontano.carreiranomundomobile.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lucasmontano.carreiranomundomobile.core.database.entity.Progress

@Dao
internal interface ProgressDao {

  @Query(
    """
    SELECT * FROM progress 
    WHERE habitId LIKE :habitId 
    AND DATE(completedAt/1000, 'unixepoch') = DATE(:completedAt/1000, 'unixepoch')
    """
  )
  suspend fun fetchProgressByHabit(habitId: String, completedAt: Long): List<Progress>

  @Query(
    """
    SELECT * FROM progress
    WHERE habitId LIKE :habitId
  """
  )
  suspend fun fetchProgressByHabit(habitId: String): List<Progress>

  @Insert
  suspend fun insert(progress: Progress)

  @Query(
    """
    DELETE FROM progress WHERE uuid = :progressId
    """
  )
  suspend fun delete(progressId: String)
}
