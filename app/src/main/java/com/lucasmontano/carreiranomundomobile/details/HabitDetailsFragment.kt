package com.lucasmontano.carreiranomundomobile.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.lucasmontano.carreiranomundomobile.core.model.HabitHistoryDomain
import com.lucasmontano.carreiranomundomobile.databinding.FragmentHabitDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class HabitDetailsFragment : Fragment() {

    private var _binding: FragmentHabitDetailsBinding? = null
    private lateinit var viewModel: HabitDetailsViewModel
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HabitDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lateinit var details: HabitHistoryDomain
        runBlocking {
            details = viewModel.getHabitDetails()
        }

        binding.titleTextView.text = details.habit.title
        binding.daysChipGroup.children.forEachIndexed { i, chip ->
            if (details.habit.daysOfWeek.contains(i + 1)) {
                (chip as Chip).isChecked = true
            } else {
                chip.visibility = View.GONE
            }
        }

        val formatter = DateTimeFormatter.ofPattern("dd, MMMM yyyy")
            .withZone(ZoneId.systemDefault())
        details.history.forEach {
            val completed = TextView(requireContext())
            val calendarDate = Calendar.getInstance()
            calendarDate.timeInMillis = it.completedAt
            completed.text =
                String.format("Completado: %1s", formatter.format(calendarDate.toInstant()))
            binding.historyLayout.addView(completed)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}