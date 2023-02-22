package com.example.experiments.quotes.threads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentQuotesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A [Fragment] that displays a class experiment.
 */
@AndroidEntryPoint
internal class ThreadsQuotesFragment : Fragment() {

  private var _binding: FragmentQuotesBinding? = null

  private val binding get() = _binding!!

  private lateinit var renderer: ThreadsQuotesRenderer

  @Inject
  lateinit var threadsQuotesRepository: ThreadsQuotesRepository

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    _binding = FragmentQuotesBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onResume() {
    super.onResume()
    renderer = ThreadsQuotesRenderer(requireActivity(), binding, threadsQuotesRepository)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    renderer.tearDown()
    _binding = null
  }
}
