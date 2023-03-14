package com.example.experiments.quotes.rxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucasmontano.carreiranomundomobile.features.experiments.databinding.FragmentQuotesBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * A [Fragment] that displays a class experiment.
 */
@AndroidEntryPoint
internal class RxJavaQuotesFragment : Fragment() {

  private var _binding: FragmentQuotesBinding? = null

  private val binding get() = _binding!!

  private val renderer = RxJavaQuotesRenderer(Schedulers.io(), AndroidSchedulers.mainThread())

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    _binding = FragmentQuotesBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    renderer.bind(binding)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    renderer.tearDown()
    _binding = null
  }
}
