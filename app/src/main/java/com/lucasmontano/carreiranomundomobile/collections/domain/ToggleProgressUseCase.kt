package com.lucasmontano.carreiranomundomobile.collections.domain

interface ToggleProgressUseCase {

  suspend operator fun invoke(habitId: String)
}
