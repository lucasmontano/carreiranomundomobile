package com.lucasmontano.carreiranomundomobile.features.collection.domain

interface ToggleProgressUseCase {

  suspend operator fun invoke(habitId: String)
}
