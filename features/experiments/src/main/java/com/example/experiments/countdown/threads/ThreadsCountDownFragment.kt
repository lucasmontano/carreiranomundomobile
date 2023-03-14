package com.example.experiments.countdown.threads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.experiments.countdown.threads.ThreadsCountdownRenderer
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentCountdownBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A [Fragment] that displays a class experiment.
 */
@AndroidEntryPoint
internal class ThreadsCountDownFragment : Fragment() {

  private var _binding: FragmentCountdownBinding? = null

  private val binding get() = _binding!!

  private lateinit var renderer: ThreadsCountdownRenderer

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    _binding = FragmentCountdownBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    renderer = ThreadsCountdownRenderer(requireActivity(), binding)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    renderer.tearDown()
    _binding = null
  }
}
