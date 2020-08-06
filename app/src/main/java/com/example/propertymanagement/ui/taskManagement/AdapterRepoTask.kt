package com.example.propertymanagement.ui.taskManagement

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagement.databinding.RowAdapterTaskBinding
import com.example.propertymanagement.models.TaskRepository

class AdapterRepoTask (private var mContext: Context, private var taskList: ArrayList<TaskRepository>):
RecyclerView.Adapter<AdapterRepoTask.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowAdapterTaskBinding.inflate(LayoutInflater.from(mContext))
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var task = taskList[position]
        holder.bind(task)
    }

    inner class MyViewHolder(private val binding: RowAdapterTaskBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(task: TaskRepository){
            binding.item = task
            binding.executePendingBindings()
            binding.adapter = this@AdapterRepoTask
        }
    }

    fun setData(list: ArrayList<TaskRepository>){
        taskList = list
        notifyDataSetChanged()
    }
}