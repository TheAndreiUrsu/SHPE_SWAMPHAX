package com.example.shpe_swamphax

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private var _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>> = _taskList

    init {
        getTasks()
    }

    fun getTasks(){
        _taskList.value = TaskManager.getTasks().reversed()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTask(title: String){
        TaskManager.addTask(title)
        getTasks()
    }

    fun deleteTask(id: Int){
        TaskManager.deleteTask(id)
        getTasks()
    }

}