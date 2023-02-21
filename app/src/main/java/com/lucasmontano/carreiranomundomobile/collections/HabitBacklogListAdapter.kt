package com.lucasmontano.carreiranomundomobile.collections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.lucasmontano.carreiranomundomobile.collections.model.HabitBacklogItem
import com.lucasmontano.carreiranomundomobile.databinding.HabitBacklogItemBinding

class HabitBacklogListAdapter(
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<HabitBacklogListAdapter.ViewHolder>() {
    private val asyncListDiffer: AsyncListDiffer<HabitBacklogItem> =
        AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HabitBacklogItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    fun updateHabits(habits: List<HabitBacklogItem>) {
        asyncListDiffer.submitList(habits)
    }

    class ViewHolder(
        private val binding: HabitBacklogItemBinding,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { onItemClick(adapterPosition) }
        }

        fun bind(habit: HabitBacklogItem) {
            binding.backlogTitleTextView.text = habit.title
            binding.backlogDaysChipGroup.clearCheck()
            binding.backlogDaysChipGroup.children.forEachIndexed { index, item ->
                if (habit.daysOfWeek.contains(index + 1)) {
                    (item as Chip).isChecked = true
                } else {
                    item.visibility = View.GONE
                }
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<HabitBacklogItem>() {

        override fun areItemsTheSame(
            oldItem: HabitBacklogItem,
            newItem: HabitBacklogItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HabitBacklogItem,
            newItem: HabitBacklogItem
        ): Boolean {
            return (oldItem.title == newItem.title) && (oldItem.daysOfWeek == newItem.daysOfWeek)
        }
    }
}