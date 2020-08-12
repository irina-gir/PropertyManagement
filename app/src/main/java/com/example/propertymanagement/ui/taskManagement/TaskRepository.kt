package com.example.propertymanagement.ui.taskManagement

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagement.models.TaskRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TaskTodoRepository {

    var listTaskRepository = MutableLiveData<List<TaskRepository>>()
    var userid = "102"
    var database = FirebaseDatabase.getInstance()
    var databaseReference = database.getReference("task")

    fun addData(task: TaskRepository){
        databaseReference.child(userid!!).setValue(task)
    }

    fun getMutableLiveData(): MutableLiveData<List<TaskRepository>>{
        readData()
        return listTaskRepository
    }

     fun readData(){
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.d("abc", "error")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var list: ArrayList<TaskRepository> = ArrayList()
                for(data in dataSnapshot.children){
                    var task = data.getValue(TaskRepository::class.java)
                    list.add(task!!)
                }
                Log.d("abc", list.toString())
                listTaskRepository.postValue(list)
            }
        } )
    }

    fun deleteData(task: TaskRepository){
        databaseReference.child(userid!!).removeValue()
        databaseReference.child(userid!!).setValue(null)
        //list.remove(task)
    }

    fun editData(task: TaskRepository){
        databaseReference.child(userid!!).setValue(task)
    }
}