package com.mytasks.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val priority: Priority,
    val status: TaskStatus,
    val createdDate: Long = System.currentTimeMillis(),
    val dueDate: Long? = null,
    val completedDate: Long? = null
)

enum class Priority(val displayName: String, val colorCode: String) {
    HIGH("High", "#F44336"),      // Red
    MEDIUM("Medium", "#FF9800"),   // Orange
    LOW("Low", "#4CAF50")          // Green
}

enum class TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED
}
