package com.mytasks.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mytasks.app.data.Priority
import com.mytasks.app.data.Task
import com.mytasks.app.data.TaskDatabase
import com.mytasks.app.data.TaskStatus
import com.mytasks.app.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>
    val pendingTasks: LiveData<List<Task>>
    val inProgressTasks: LiveData<List<Task>>
    val completedTasks: LiveData<List<Task>>
    val pendingCount: LiveData<Int>
    val inProgressCount: LiveData<Int>
    val completedCount: LiveData<Int>
    
    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks.asLiveData()
        pendingTasks = repository.getTasksByStatus(TaskStatus.PENDING).asLiveData()
        inProgressTasks = repository.getTasksByStatus(TaskStatus.IN_PROGRESS).asLiveData()
        completedTasks = repository.getTasksByStatus(TaskStatus.COMPLETED).asLiveData()
        pendingCount = repository.getTaskCountByStatus(TaskStatus.PENDING).asLiveData()
        inProgressCount = repository.getTaskCountByStatus(TaskStatus.IN_PROGRESS).asLiveData()
        completedCount = repository.getTaskCountByStatus(TaskStatus.COMPLETED).asLiveData()
    }
    
    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }
    
    fun updateTask(task: Task) = viewModelScope.launch {
        repository.updateTask(task)
    }
    
    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task)
    }
    
    suspend fun getTaskById(taskId: Long): Task? {
        return repository.getTaskById(taskId)
    }
}
