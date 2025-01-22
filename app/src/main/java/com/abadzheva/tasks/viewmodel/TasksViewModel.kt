package com.abadzheva.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abadzheva.tasks.model.Task
import com.abadzheva.tasks.model.TaskDao
import kotlinx.coroutines.launch

class TasksViewModel(
    val dao: TaskDao,
) : ViewModel() {
    var newTaskName = ""

    val tasks = dao.getAll()

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }
}
