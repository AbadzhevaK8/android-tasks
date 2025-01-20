package com.abadzheva.tasks.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.abadzheva.tasks.R
import com.abadzheva.tasks.model.Task
import com.abadzheva.tasks.presentation.TaskItemAdapter.TaskItemViewHolder

class TaskItemAdapter : Adapter<TaskItemViewHolder>() {
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(
        holder: TaskItemViewHolder,
        position: Int,
    ) {
        val item = data[position]
        holder.bind(item)
    }

    class TaskItemViewHolder(
        val rootView: TextView,
    ) : RecyclerView.ViewHolder(rootView) {
        fun bind(item: Task) {
            rootView.text = item.taskName
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view =
                    layoutInflater
                        .inflate(R.layout.task_item, parent, false) as TextView
                return TaskItemViewHolder(view)
            }
        }
    }
}
