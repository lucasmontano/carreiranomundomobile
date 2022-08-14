package com.lucasmontano.carreiranomundomobile.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasmontano.carreiranomundomobile.databinding.FragmentHabitListBinding

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
    // Adding Space between items with ItemDecoration
    binding.habitRecyclerView.addItemDecoration(HabitListItemDecoration(requireContext()))
    // Updating the list of habits
    adapter.updateHabits(MockHabits.habitItemList)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
