package com.mytasks.app.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mytasks.app.R
import com.mytasks.app.data.Priority
import com.mytasks.app.data.Task
import com.mytasks.app.data.TaskStatus
import com.mytasks.app.databinding.ActivityTaskDetailBinding
import com.mytasks.app.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import java.util.*

class TaskDetailActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityTaskDetailBinding
    private val viewModel: TaskViewModel by viewModels()
    private var currentTask: Task? = null
    private var taskId: Long = -1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        taskId = intent.getLongExtra("TASK_ID", -1)
        
        setupSpinners()
        loadTaskIfExists()
        setupButtons()
    }
    
    private fun setupSpinners() {
        val priorityAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Priority.values().map { it.displayName }
        )
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.prioritySpinner.adapter = priorityAdapter
        
        val statusAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            TaskStatus.values().map { it.name.replace("_", " ") }
        )
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.statusSpinner.adapter = statusAdapter
    }
    
    private fun loadTaskIfExists() {
        if (taskId != -1L) {
            lifecycleScope.launch {
                currentTask = viewModel.getTaskById(taskId)
                currentTask?.let { task ->
                    binding.titleInput.setText(task.title)
                    binding.descriptionInput.setText(task.description)
                    binding.prioritySpinner.setSelection(task.priority.ordinal)
                    binding.statusSpinner.setSelection(task.status.ordinal)
                    title = "Edit Task"
                }
            }
        } else {
            title = "New Task"
        }
    }
    
    private fun setupButtons() {
        binding.saveButton.setOnClickListener {
            saveTask()
        }
        
        binding.deleteButton.setOnClickListener {
            currentTask?.let { task ->
                viewModel.deleteTask(task)
                Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    
    private fun saveTask() {
        val title = binding.titleInput.text.toString().trim()
        val description = binding.descriptionInput.text.toString().trim()
        
        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }
        
        val priority = Priority.values()[binding.prioritySpinner.selectedItemPosition]
        val status = TaskStatus.values()[binding.statusSpinner.selectedItemPosition]
        
        val task = if (currentTask != null) {
            currentTask!!.copy(
                title = title,
                description = description,
                priority = priority,
                status = status,
                completedDate = if (status == TaskStatus.COMPLETED) System.currentTimeMillis() else null
            )
        } else {
            Task(
                title = title,
                description = description,
                priority = priority,
                status = status
            )
        }
        
        if (currentTask != null) {
            viewModel.updateTask(task)
            Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.insertTask(task)
            Toast.makeText(this, "Task created", Toast.LENGTH_SHORT).show()
        }
        
        finish()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
