package com.lucasmontano.carreiranomundomobile.features.collection

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class HabitListLifecycleObserver(
  private val viewModel: HabitListViewModel
) : DefaultLifecycleObserver {

  override fun onResume(owner: LifecycleOwner) {
    super.onResume(owner)
    viewModel.onResume()
  }
}
