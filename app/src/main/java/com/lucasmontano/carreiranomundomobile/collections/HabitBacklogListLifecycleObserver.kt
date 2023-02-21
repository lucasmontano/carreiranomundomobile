package com.lucasmontano.carreiranomundomobile.collections

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class HabitBacklogListLifecycleObserver(
  private val viewModel: HabitBacklogListViewModel
) : DefaultLifecycleObserver {

  override fun onResume(owner: LifecycleOwner) {
    super.onResume(owner)
    viewModel.onResume()
  }
}
