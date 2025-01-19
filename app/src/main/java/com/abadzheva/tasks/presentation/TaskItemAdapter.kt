package com.abadzheva.tasks.presentation

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.abadzheva.tasks.model.Task
import com.abadzheva.tasks.presentation.TaskItemAdapter.TaskItemViewHolder

class TaskItemAdapter : Adapter<TaskItemViewHolder>() {
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    class TaskItemViewHolder(
        val rootView: TextView,
    ) : RecyclerView.ViewHolder(rootView)
}
