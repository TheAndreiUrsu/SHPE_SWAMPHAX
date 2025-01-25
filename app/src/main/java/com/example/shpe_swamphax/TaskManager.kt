package com.example.shpe_swamphax

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date

object TaskManager {
    private var taskId = 0;

    private val tasks = mutableListOf<Task>()

    fun getTasks() : List<Task>{
        return tasks
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTask(title: String){
        tasks.add(Task(taskId++, title, Date.from(Instant.now())))
    }

    fun deleteTask(id: Int){
        tasks.removeIf {
            it.id == id
        }
    }
}