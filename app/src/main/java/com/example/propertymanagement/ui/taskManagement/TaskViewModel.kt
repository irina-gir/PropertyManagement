package com.example.propertymanagement.ui.taskManagement

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.models.TaskRepository

class TaskViewModel : ViewModel(){

    private val repo = TaskTodoRepository()
    var priority = ObservableField<String>()
    var summary = ObservableField<String>()
    var description = ObservableField<String>()
    var property = ObservableField<String>()
    var dueDate = ObservableField<String>()
    var assignTo = ObservableField<String>()

    fun getTaskRepositoryObserver() =repo.getMutableLiveData()

    //delete data from firebase
    fun onButtonDeleteClicked(task: TaskRepository){
        repo.deleteData(task)
    }

    //submit data to the firebase
    fun onButtonSaveClicked(){
        var task = TaskRepository(priority.get().toString(),
            summary.get().toString(),
            description.get().toString(),
            property.get().toString(),
            dueDate.get().toString(),
            assignTo.get().toString())
        repo.addData(task)
    }

    //edit data from the firebase
    fun onButtonEditSaveClicked(){
        var newTask = TaskRepository(priority.get().toString(),
            summary.get().toString(),
            description.get().toString(),
            property.get().toString(),
            dueDate.get().toString(),
            assignTo.get().toString())

        repo.editData(newTask)
    }

}