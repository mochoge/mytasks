package com.mytasks.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.mytasks.app.R
import com.mytasks.app.data.TaskStatus
import com.mytasks.app.databinding.ActivityMainBinding
import com.mytasks.app.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        
        setupRecyclerView()
        setupTabs()
        setupFab()
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        adapter = TaskAdapter { task ->
            val intent = Intent(this, TaskDetailActivity::class.java).apply {
                putExtra("TASK_ID", task.id)
            }
            startActivity(intent)
        }
        
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
    
    private fun setupTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("All"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Pending"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("In Progress"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Completed"))
        
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> viewModel.allTasks.observe(this@MainActivity) { adapter.submitList(it) }
                    1 -> viewModel.pendingTasks.observe(this@MainActivity) { adapter.submitList(it) }
                    2 -> viewModel.inProgressTasks.observe(this@MainActivity) { adapter.submitList(it) }
                    3 -> viewModel.completedTasks.observe(this@MainActivity) { adapter.submitList(it) }
                }
            }
            
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
    
    private fun setupFab() {
        binding.fab.setOnClickListener {
            val intent = Intent(this, TaskDetailActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun observeViewModel() {
        viewModel.allTasks.observe(this) { tasks ->
            adapter.submitList(tasks)
        }
        
        updateSummary()
    }
    
    private fun updateSummary() {
        viewModel.pendingCount.observe(this) { count ->
            binding.pendingCountText.text = count.toString()
        }
        
        viewModel.inProgressCount.observe(this) { count ->
            binding.inProgressCountText.text = count.toString()
        }
        
        viewModel.completedCount.observe(this) { count ->
            binding.completedCountText.text = count.toString()
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_daily_brief -> {
                startActivity(Intent(this, DailyBriefActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
