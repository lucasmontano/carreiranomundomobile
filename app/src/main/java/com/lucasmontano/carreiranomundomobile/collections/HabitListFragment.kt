package com.lucasmontano.carreiranomundomobile.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.lucasmontano.carreiranomundomobile.R
import com.lucasmontano.carreiranomundomobile.collections.domain.GetHabitsForTodayUseCaseImpl
import com.lucasmontano.carreiranomundomobile.collections.domain.ToggleProgressUseCaseImpl
import com.lucasmontano.carreiranomundomobile.core.repository.HabitRepositoryImpl
import com.lucasmontano.carreiranomundomobile.core.repository.ProgressRepositoryImpl
import com.lucasmontano.carreiranomundomobile.databinding.FragmentHabitListBinding

/**
 * A [Fragment] that displays a list of habits.
 */
class HabitListFragment : Fragment() {

  private var _binding: FragmentHabitListBinding? = null

  private val binding get() = _binding!!

  private lateinit var adapter: HabitListAdapter

  private val viewModel: HabitListViewModel by activityViewModels {
    val habitRepository = HabitRepositoryImpl
    val progressRepository = ProgressRepositoryImpl
    val getHabitsForTodayUseCase = GetHabitsForTodayUseCaseImpl(
      progressRepository = progressRepository,
      habitRepository = habitRepository,
    )
    HabitListViewModel.Factory(
      getHabitsForTodayUseCase = getHabitsForTodayUseCase,
      toggleProgressUseCase = ToggleProgressUseCaseImpl(progressRepository)
    )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycle.addObserver(HabitListLifecycleObserver(viewModel))
    adapter = HabitListAdapter(viewModel)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    _binding = FragmentHabitListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    // Set the adapter
    binding.habitRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    binding.habitRecyclerView.adapter = adapter

    // Adding decorations to our recycler view
    addingDividerDecoration()

    // Observer UI State for changes.
    viewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
      bindUiState(it)
    }

    // Set Navigation Fab
    binding.fab.setOnClickListener {
      findNavController().navigate(R.id.action_habitList_to_habitForm)
    }
  }

  /**
   * Bind UI State to View.
   *
   * Update list of habits according to updates.
   */
  private fun bindUiState(uiState: HabitListViewModel.UiState) {
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

    binding.habitRecyclerView.addItemDecoration(divider)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
