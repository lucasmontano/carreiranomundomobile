package com.example.experiments.gpt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentQuotesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer


/**
 * A [Fragment] that displays a class experiment.
 */
@AndroidEntryPoint
internal class GPTFragment : Fragment() {

  private var _binding: FragmentQuotesBinding? = null

  private val binding get() = _binding!!

  private val viewModel: HabitTipsViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    _binding = FragmentQuotesBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
      when (uiState) {
        is HabitTipsViewModel.UiState.Loading -> {
          binding.contentLoadingProgressBar.show()
        }
        is HabitTipsViewModel.UiState.Success -> {
          binding.experimentTextView.text = uiState.tips
          binding.contentLoadingProgressBar.hide()
        }
        is HabitTipsViewModel.UiState.Error -> {
          val exception = uiState.exception
          binding.experimentTextView.text = "Error: ${exception.message}"
          binding.contentLoadingProgressBar.hide()
        }
      }
    }

    viewModel.loadHabitTip()
  }


  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
