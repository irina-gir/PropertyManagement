package com.example.propertymanagement.ui.taskManagement

import com.example.propertymanagement.models.TaskRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TaskTodoRepository {

    lateinit var listTask: MutableList<List<TaskRepository>>
    var list: ArrayList<TaskRepository> = ArrayList()

    var userid = "102"
    var database = FirebaseDatabase.getInstance()
    var databaseReference = database.getReference("task")

    fun addData(task: TaskRepository){
        databaseReference.child(userid!!).setValue(task)
    }

    fun readData(): ArrayList<TaskRepository>{
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(data in dataSnapshot.children){
                    var task = data.getValue(TaskRepository::class.java)
                    list.add(task!!)
                }
            }

        } )
        return list
    }
}