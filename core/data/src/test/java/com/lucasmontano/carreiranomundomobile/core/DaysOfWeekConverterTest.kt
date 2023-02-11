package com.lucasmontano.carreiranomundomobile.core

import com.lucasmontano.carreiranomundomobile.core.database.DaysOfWeekConverter
import org.junit.Assert.assertEquals
import org.junit.Test

class DaysOfWeekConverterTest {

  private val converter = DaysOfWeekConverter()

  @Test
  fun `fromDaysOfWeek should convert list of integers to a string`() {
    val daysOfWeek = listOf(1, 2, 3, 4, 5)
    val expected = "1,2,3,4,5"
    val result = converter.fromDaysOfWeek(daysOfWeek)
    assertEquals(expected, result)
  }

  @Test
  fun `toDaysOfWeek should convert string to list of integers`() {
    val daysOfWeek = "1,2,3,4,5"
    val expected = listOf(1, 2, 3, 4, 5)
    val result = converter.toDaysOfWeek(daysOfWeek)
    assertEquals(expected, result)
  }
}
