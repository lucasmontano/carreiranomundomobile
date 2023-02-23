package com.lucasmontano.carreiranomundomobile.features.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.lucasmontano.carreiranomundomobile.collections.HabitBacklogListLifecycleObserver
import com.lucasmontano.carreiranomundomobile.collections.HabitBacklogListViewModel
import com.lucasmontano.carreiranomundomobile.features.collection.databinding.FragmentHabitBacklogListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HabitBacklogListFragment : Fragment() {

    private var _binding: FragmentHabitBacklogListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: HabitBacklogListAdapter

    private lateinit var viewModel: HabitBacklogListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[HabitBacklogListViewModel::class.java]

        lifecycle.addObserver(HabitBacklogListLifecycleObserver(viewModel))
        adapter = HabitBacklogListAdapter() {
            val uuid = viewModel.onBacklogItemClick(it)
            val request = NavDeepLinkRequest.Builder
                .fromUri("habit-app://com.lucasmontano.carreiranomundomobile/habit/$uuid".toUri())
                .build()
            findNavController().navigate(request)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitBacklogListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the adapter
        binding.habitBacklogRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.habitBacklogRecyclerView.adapter = adapter

        // Adding decorations to our recycler view
        addingDividerDecoration()

        // Observer UI State for changes.
        viewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
            bindUiState(it)
        }
    }


    private fun bindUiState(uiState: HabitBacklogListViewModel.UiState) {
        adapter.updateHabits(uiState.habitItemList)
    }

    private fun addingDividerDecoration() {
        // Adding Line between items with MaterialDividerItemDecoration
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        // Adding the line at the end of the list
        divider.isLastItemDecorated = true

        val resources = requireContext().resources

        // Adding start spacing
        divider.dividerInsetStart = resources.getDimensionPixelSize(R.dimen.horizontal_margin)

        // Defining size of the line
        divider.dividerThickness = resources.getDimensionPixelSize(R.dimen.divider_height)
        divider.dividerColor = ContextCompat.getColor(requireContext(), R.color.primary_200)

        binding.habitBacklogRecyclerView.addItemDecoration(divider)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}