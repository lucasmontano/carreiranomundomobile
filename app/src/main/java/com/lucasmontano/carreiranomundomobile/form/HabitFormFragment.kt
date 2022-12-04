package com.lucasmontano.carreiranomundomobile.form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lucasmontano.carreiranomundomobile.collections.HabitListViewModel
import com.lucasmontano.carreiranomundomobile.databinding.FragmentHabitFormBinding
import com.lucasmontano.carreiranomundomobile.dummy.MockHabits

/**
 * A [Fragment] that displays a list of habits.
 */
class HabitFormFragment : Fragment() {

  private var _binding: FragmentHabitFormBinding? = null

  private val binding get() = _binding!!

  private val viewModel: HabitListViewModel by activityViewModels {
    HabitListViewModel.Factory(MockHabits)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    _binding = FragmentHabitFormBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    // Save Habit and Navigate Up
    binding.saveButton.setOnClickListener { onSave() }
  }

  private fun onSave() {
    // Get value from the input to save
    val habitName = binding.titleTextInput.editText?.text.toString()

    // Get period selected: where 1 is Monday and 7 is Sunday.
    val habitDaysSelected = binding.daysChipGroup.checkedChipIds

    // Use ViewModel to add the new Habit
    viewModel.addHabit(habitName, habitDaysSelected)

    // Navigate Up in the navigation three, meaning: goes back
    findNavController().navigateUp()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
