package com.example.propertymanagement.ui.taskManagement

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.models.TaskRepository
import kotlinx.android.synthetic.main.fragment_add_task.view.*

class TaskViewModel : ViewModel(){

    private val repo = TaskTodoRepository()
    val priority = ObservableField<String>()
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
        Log.d("abc", "${priority.get()}")
        println("Priority is ${priority.get()}")
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
//        priority.set(task.priority)
//        summary.set(task.summary)
//        description.set(task.description)
//        property.set(task.property)
//        dueDate.set(task.dueDate)
//        assignTo.set(task.assignTo)

        var newTask = TaskRepository(priority.get().toString(),
            summary.get().toString(),
            description.get().toString(),
            property.get().toString(),
            dueDate.get().toString(),
            assignTo.get().toString())

        repo.editData(newTask)
    }

}