package com.lucasmontano.carreiranomundomobile.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lucasmontano.carreiranomundomobile.core.database.DaysOfWeekConverter

@Entity(tableName = "habit")
data class Habit(
  @PrimaryKey val uuid: String,
  @ColumnInfo(name = "title") val title: String,
  @TypeConverters(DaysOfWeekConverter::class)
  @ColumnInfo(name = "daysOfWeek") val daysOfWeek: List<Int>
)

