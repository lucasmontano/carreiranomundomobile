package com.lucasmontano.carreiranomundomobile.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.lucasmontano.carreiranomundomobile.R
import com.lucasmontano.carreiranomundomobile.databinding.FragmentHabitListBinding
import com.lucasmontano.carreiranomundomobile.dummy.MockHabits

/**
 * A [Fragment] that displays a list of habits.
 */
class HabitListFragment : Fragment() {

  private var _binding: FragmentHabitListBinding? = null

  private val binding get() = _binding!!

  private lateinit var adapter: HabitListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    adapter = HabitListAdapter()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
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

    // Updating the list of habits
    adapter.updateHabits(MockHabits.habitItemList)
  }

  private fun addingDividerDecoration() {
    // Adding Line between items with MaterialDividerItemDecoration
    val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

    // Removing the line at the end of the list
    divider.isLastItemDecorated = false

    val resources = requireContext().resources

    // Adding start spacing
    divider.dividerInsetStart = resources.getDimensionPixelSize(R.dimen.horizontal_margin)

    // Defining size of the line
    divider.dividerThickness = resources.getDimensionPixelSize(R.dimen.divider_height)
    divider.dividerColor = ContextCompat.getColor(requireContext(), R.color.primary_200)

    binding.habitRecyclerView.addItemDecoration(divider)
  }

  private fun addingDividerSpace() {
    binding.habitRecyclerView.addItemDecoration(HabitListItemDecoration(requireContext()))
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
