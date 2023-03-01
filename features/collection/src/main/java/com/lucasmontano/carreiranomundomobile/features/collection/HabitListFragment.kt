package com.lucasmontano.carreiranomundomobile.features.collection

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.lucasmontano.carreiranomundomobile.features.collection.databinding.FragmentHabitListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A [Fragment] that displays a list of habits.
 */
@AndroidEntryPoint
class HabitListFragment : Fragment() {

  private var _binding: FragmentHabitListBinding? = null

  private val binding get() = _binding!!

  private lateinit var adapter: HabitListAdapter

  private lateinit var viewModel: HabitListViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewModel = ViewModelProvider(this)[HabitListViewModel::class.java]

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

    val menuHost = requireActivity()
    menuHost.addMenuProvider(object : MenuProvider {
      override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
      }

      override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.menu_item_preferences) {
          findNavController().navigate(R.id.action_habitListFragment_to_habitBacklogListFragment)
          return true
        }
        return false
      }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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
