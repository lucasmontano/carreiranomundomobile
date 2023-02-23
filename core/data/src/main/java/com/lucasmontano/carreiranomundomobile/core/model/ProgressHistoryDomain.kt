package com.lucasmontano.carreiranomundomobile.core.model

data class ProgressHistoryDomain(
  val id: String,
  val habitId: String,
  val completedAt: Long,
)
