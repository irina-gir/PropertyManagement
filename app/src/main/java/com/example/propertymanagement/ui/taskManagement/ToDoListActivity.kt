package com.example.propertymanagement.ui.taskManagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityToDoListBinding
import com.example.propertymanagement.models.TaskRepository
import com.example.propertymanagement.utils.toolbar
import kotlinx.android.synthetic.main.activity_to_do_list.*
import kotlinx.android.synthetic.main.row_adapter_task.view.*

class ToDoListActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var binding: ActivityToDoListBinding
    private lateinit var taskList: ArrayList<TaskRepository>
    private lateinit var adapterRepoTask: AdapterRepoTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_to_do_list)

        viewModel= ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.viewModel = viewModel

        this.toolbar("ToDo List")

        init()

    }

    private fun init() {

        initList()
        button_add_task.setOnClickListener {
            recycler_view_task.visibility = View.GONE
            val fragment = AddTaskFragment()
            var transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

    private fun initList() {
        observeData()
        var fragment = supportFragmentManager
        adapterRepoTask = AdapterRepoTask(this, fragment)
        binding.recyclerViewTask.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTask.apply {
            adapter = adapterRepoTask

        }
    }

    private fun observeData() {
        viewModel.getTaskRepositoryObserver().observe(this, Observer {
            if(it != null){
                taskList = it as ArrayList<TaskRepository>
                adapterRepoTask.setData(taskList)
                adapterRepoTask.notifyDataSetChanged()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }
}