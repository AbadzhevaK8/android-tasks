package com.abadzheva.tasks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }

    fun onTaskClicked(taskId: Long) {
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated() {
        _navigateToTask.value = null
    }
}
