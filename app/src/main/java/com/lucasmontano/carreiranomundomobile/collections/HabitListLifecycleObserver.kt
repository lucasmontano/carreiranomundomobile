package com.lucasmontano.carreiranomundomobile.collections

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
