package com.mytasks.app.utils

import com.mytasks.app.data.Priority
import com.mytasks.app.data.Task
import com.mytasks.app.data.TaskStatus
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object TaskExtractor {
    
    private val datePatterns = listOf(
        // Tomorrow, today patterns
        Pattern.compile("\\b(tomorrow|today)\\b", Pattern.CASE_INSENSITIVE),
        // Date patterns like "Jan 15", "January 15"
        Pattern.compile("\\b(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)[a-z]* \\d{1,2}\\b", Pattern.CASE_INSENSITIVE),
        // Patterns like "next week", "next monday"
        Pattern.compile("\\bnext (week|monday|tuesday|wednesday|thursday|friday|saturday|sunday)\\b", Pattern.CASE_INSENSITIVE)
    )
    
    private val priorityKeywords = mapOf(
        Priority.HIGH to listOf("urgent", "important", "asap", "critical", "high priority"),
        Priority.MEDIUM to listOf("medium", "moderate"),
        Priority.LOW to listOf("low", "when possible", "sometime")
    )
    
    fun extractTasksFromBrief(briefText: String): List<Task> {
        val tasks = mutableListOf<Task>()
        
        // Split by sentences or lines that might indicate separate tasks
        val sentences = briefText.split(Regex("[.!?\\n]"))
            .map { it.trim() }
            .filter { it.isNotEmpty() && it.length > 10 }
        
        for (sentence in sentences) {
            // Check if sentence contains action verbs indicating a task
            if (containsTaskIndicators(sentence)) {
                val priority = extractPriority(sentence)
                val dueDate = extractDueDate(sentence)
                
                tasks.add(
                    Task(
                        title = cleanTaskTitle(sentence),
                        description = sentence,
                        priority = priority,
                        status = TaskStatus.PENDING,
                        dueDate = dueDate
                    )
                )
            }
        }
        
        return tasks
    }
    
    private fun containsTaskIndicators(text: String): Boolean {
        val taskVerbs = listOf(
            "need to", "have to", "must", "should", "will",
            "plan to", "going to", "want to", "schedule",
            "meet", "call", "email", "send", "complete",
            "finish", "prepare", "review", "update"
        )
        
        val lowerText = text.lowercase()
        return taskVerbs.any { lowerText.contains(it) }
    }
    
    private fun extractPriority(text: String): Priority {
        val lowerText = text.lowercase()
        
        for ((priority, keywords) in priorityKeywords) {
            if (keywords.any { lowerText.contains(it) }) {
                return priority
            }
        }
        
        return Priority.MEDIUM // Default priority
    }
    
    private fun extractDueDate(text: String): Long? {
        val lowerText = text.lowercase()
        val calendar = Calendar.getInstance()
        
        // Check for "tomorrow"
        if (lowerText.contains("tomorrow")) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            return calendar.timeInMillis
        }
        
        // Check for "today"
        if (lowerText.contains("today")) {
            return calendar.timeInMillis
        }
        
        // Check for "next week"
        if (lowerText.contains("next week")) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
            return calendar.timeInMillis
        }
        
        // Check for day of week
        val daysOfWeek = mapOf(
            "monday" to Calendar.MONDAY,
            "tuesday" to Calendar.TUESDAY,
            "wednesday" to Calendar.WEDNESDAY,
            "thursday" to Calendar.THURSDAY,
            "friday" to Calendar.FRIDAY,
            "saturday" to Calendar.SATURDAY,
            "sunday" to Calendar.SUNDAY
        )
        
        for ((day, calendarDay) in daysOfWeek) {
            if (lowerText.contains("next $day")) {
                calendar.add(Calendar.WEEK_OF_YEAR, 1)
                calendar.set(Calendar.DAY_OF_WEEK, calendarDay)
                return calendar.timeInMillis
            }
        }
        
        return null
    }
    
    private fun cleanTaskTitle(text: String): String {
        var title = text.trim()
        
        // Remove common prefixes
        val prefixes = listOf(
            "I need to ", "I have to ", "I must ", "I should ", "I will ",
            "Need to ", "Have to ", "Must ", "Should ", "Will "
        )
        
        for (prefix in prefixes) {
            if (title.startsWith(prefix, ignoreCase = true)) {
                title = title.substring(prefix.length)
                break
            }
        }
        
        // Capitalize first letter
        if (title.isNotEmpty()) {
            title = title[0].uppercase() + title.substring(1)
        }
        
        // Limit length for title
        if (title.length > 100) {
            title = title.substring(0, 97) + "..."
        }
        
        return title
    }
}
