package com.example.propertymanagement.ui.taskManagement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.FragmentEditTaskBinding
import com.example.propertymanagement.databinding.RowAdapterTaskBinding
import com.example.propertymanagement.models.TaskRepository
import kotlinx.android.synthetic.main.activity_to_do_list.*

class AdapterRepoTask (private var mContext: Context, private var fragManager: FragmentManager):
RecyclerView.Adapter<AdapterRepoTask.MyViewHolder>(){

    private var taskList = ArrayList<TaskRepository>()

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

            binding.buttonEditTask.setOnClickListener {
                fragManager.beginTransaction()
                    .replace(R.id.fragment_container, FragmentEditTask())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    fun setData(list: ArrayList<TaskRepository>){
        taskList = list
        notifyDataSetChanged()
    }

    fun onButtonDeleteClicked(task: TaskRepository){
        TaskViewModel().onButtonDeleteClicked(task)
        notifyDataSetChanged()
    }

}