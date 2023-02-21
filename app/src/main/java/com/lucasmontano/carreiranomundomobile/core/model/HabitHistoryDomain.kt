package com.lucasmontano.carreiranomundomobile.core.model

data class HabitHistoryDomain (
    val habit: HabitDomain,
    val history: List<ProgressHistoryDomain>
)
