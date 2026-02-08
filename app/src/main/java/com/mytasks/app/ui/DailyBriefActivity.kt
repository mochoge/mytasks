package com.mytasks.app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mytasks.app.databinding.ActivityDailyBriefBinding
import com.mytasks.app.utils.TaskExtractor
import com.mytasks.app.viewmodel.TaskViewModel

class DailyBriefActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDailyBriefBinding
    private val viewModel: TaskViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyBriefBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Daily Brief"
        
        setupButtons()
    }
    
    private fun setupButtons() {
        binding.submitButton.setOnClickListener {
            submitBrief()
        }
    }
    
    private fun submitBrief() {
        val briefText = binding.briefInput.text.toString().trim()
        
        if (briefText.isEmpty()) {
            Toast.makeText(this, "Please enter your daily brief", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Extract tasks from the brief
        val extractedTasks = TaskExtractor.extractTasksFromBrief(briefText)
        
        if (extractedTasks.isEmpty()) {
            Toast.makeText(
                this,
                "No tasks found. Brief saved!",
                Toast.LENGTH_LONG
            ).show()
            finish()
            return
        }
        
        // Save extracted tasks
        extractedTasks.forEach { task ->
            viewModel.insertTask(task)
        }
        
        Toast.makeText(
            this,
            "Brief submitted! ${extractedTasks.size} task(s) extracted.",
            Toast.LENGTH_LONG
        ).show()
        
        finish()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
