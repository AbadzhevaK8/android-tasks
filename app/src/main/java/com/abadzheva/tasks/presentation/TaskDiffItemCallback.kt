package com.abadzheva.tasks.presentation

import androidx.recyclerview.widget.DiffUtil
import com.abadzheva.tasks.model.Task

class TaskDiffItemCallback : DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(
        oldItem: Task,
        newItem: Task
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(
        oldItem: Task,
        newItem: Task
    ): Boolean {
        TODO("Not yet implemented")
    }

}