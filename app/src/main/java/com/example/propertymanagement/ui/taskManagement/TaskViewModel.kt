package com.example.propertymanagement.ui.taskManagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.models.TaskRepository

class TaskViewModel : ViewModel(){

    var listTaskRepository = MutableLiveData<List<TaskRepository>>()
    private val repo = TaskTodoRepository()
    var list: ArrayList<TaskRepository> = ArrayList()

    fun getTasks(): LiveData<List<TaskRepository>> {
        return listTaskRepository
    }

    fun passTask(task: TaskRepository){
        repo.addData(task)
    }

    fun passReadTask():ArrayList<TaskRepository>{
        list = repo.readData()
        return list
    }

}