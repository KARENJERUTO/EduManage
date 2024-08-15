package com.example.edumanage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.edumanage.databinding.ItemAssignmentBinding
import com.example.edumanage.model.Assignment

class AssignmentAdapter : ListAdapter<Assignment, AssignmentAdapter.AssignmentViewHolder>(AssignmentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val binding = ItemAssignmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssignmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        val assignment = getItem(position)
        holder.bind(assignment)
    }

    class AssignmentViewHolder(private val binding: ItemAssignmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(assignment: Assignment) {
            binding.titleTextView.text = assignment.title ?: "No title available"
            binding.dueDateTextView.text = assignment.dueDate ?: "No due date"
            binding.descriptionTextView.text = assignment.description ?: "No description available"
        }
    }


    private class AssignmentDiffCallback : DiffUtil.ItemCallback<Assignment>() {
        override fun areItemsTheSame(oldItem: Assignment, newItem: Assignment) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Assignment, newItem: Assignment) = oldItem == newItem
    }
}
