package com.lucasmontano.carreiranomundomobile.features.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lucasmontano.carreiranomundomobile.features.collection.databinding.HabitItemBinding
import com.lucasmontano.carreiranomundomobile.features.collection.model.HabitItem


/**
 * RecyclerView adapter for displaying a list of Habits.
 *
 * The UI is based on the [HabitItemBinding].
 * We use the [HabitItem] as a model for the binding.
 */
class HabitListAdapter(
  private val viewModel: HabitListViewModel
) : RecyclerView.Adapter<HabitListAdapter.ViewHolder>() {

  private val asyncListDiffer: AsyncListDiffer<HabitItem> = AsyncListDiffer(this, DiffCallback)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = HabitItemBinding.inflate(layoutInflater, parent, false)
    return ViewHolder(binding, viewModel)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(asyncListDiffer.currentList[position])
  }

  override fun getItemCount(): Int = asyncListDiffer.currentList.size

  fun updateHabits(habits: List<HabitItem>) {
    asyncListDiffer.submitList(habits)
  }

  class ViewHolder(
    private val binding: HabitItemBinding,
    private val viewModel: HabitListViewModel
  ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(habit: HabitItem) {
      binding.titleTextView.text = habit.title
      binding.completeCheckBox.isChecked = habit.isCompleted

      binding.completeCheckBox.setOnClickListener {
        viewModel.toggleHabitCompleted(habit.id)
      }
    }
  }

  object DiffCallback : DiffUtil.ItemCallback<HabitItem>() {

    override fun areItemsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
      return oldItem.isCompleted == newItem.isCompleted
    }
  }
}
