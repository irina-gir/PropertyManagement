package com.example.propertymanagement.models

import java.io.Serializable

class TaksApiModel: ArrayList<TaskRepository>()

data class TaskRepository(
    val priority: String? = null,
    var summary: String? = null,
    val description: String? = null,
    val property: String? = null,
    val dueDate: String? = null,
    val assignTo: String? = null,
    var id:String? =null
):Serializable{
    companion object{
        const val KEY_TASK = "task"
    }
}