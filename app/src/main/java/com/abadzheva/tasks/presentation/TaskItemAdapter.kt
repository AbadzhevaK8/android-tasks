package com.abadzheva.tasks.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abadzheva.tasks.databinding.TaskItemBinding
import com.abadzheva.tasks.model.Task
import com.abadzheva.tasks.presentation.TaskItemAdapter.TaskItemViewHolder

class TaskItemAdapter : ListAdapter<Task, TaskItemViewHolder>(TaskDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(
        holder: TaskItemViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TaskItemViewHolder(
        val binding: TaskItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Task) {
            binding.task = item
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskItemViewHolder(binding)
            }
        }
    }
}
