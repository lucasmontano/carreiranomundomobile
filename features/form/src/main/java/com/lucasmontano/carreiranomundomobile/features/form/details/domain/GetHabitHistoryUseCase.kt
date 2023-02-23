package com.lucasmontano.carreiranomundomobile.features.form.details.domain

import com.lucasmontano.carreiranomundomobile.core.model.HabitHistoryDomain

interface GetHabitHistoryUseCase {
    suspend operator fun invoke(uuid: String): HabitHistoryDomain
}