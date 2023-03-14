package com.example.experiments.quotes.coroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentQuotesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers

/**
 * A [Fragment] that displays a class experiment.
 */
@AndroidEntryPoint
internal class QuotesFragment : Fragment() {

  private var _binding: FragmentQuotesBinding? = null

  private val binding get() = _binding!!

  private lateinit var renderer: QuotesRenderer

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    _binding = FragmentQuotesBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    renderer = QuotesRenderer(lifecycleScope, Dispatchers.IO, binding)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
