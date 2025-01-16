package com.abadzheva.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.abadzheva.tasks.model.Task
import com.abadzheva.tasks.model.TaskDao
import kotlinx.coroutines.launch

class TasksViewModel(
    val dao: TaskDao,
) : ViewModel() {
    var newTaskName = ""

    private val tasks = dao.getAll()
    val tasksString =
        tasks.map { tasks ->
            formatTasks(tasks)
        }

    private fun formatTasks(tasks: @JvmSuppressWildcards List<Task>) =
        tasks.fold("") { str, item ->
            str + '\n' + formatTask(item)
        }

    private fun formatTask(task: Task): String {
        var str = "ID: ${task.taskId}"
        str += '\n' + "Name: ${task.taskName}"
        str += '\n' + "Done: ${task.taskDone}"
        return str
    }

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }
}
