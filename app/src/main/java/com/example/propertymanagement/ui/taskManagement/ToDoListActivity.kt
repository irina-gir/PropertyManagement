package com.example.propertymanagement.ui.taskManagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityToDoListBinding
import com.example.propertymanagement.models.TaskRepository
import kotlinx.android.synthetic.main.activity_to_do_list.*

class ToDoListActivity : AppCompatActivity(), AddTaskFragment.OnFragmentInteraction {

    private lateinit var taskRepo:TaskRepository
    private lateinit var viewModel: TaskViewModel
    private lateinit var binding: ActivityToDoListBinding
    private lateinit var taskList: ArrayList<TaskRepository>
    private lateinit var adapterRepoTask: AdapterRepoTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_to_do_list)

        viewModel= ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.viewModel = viewModel

        init()

    }

    private fun init() {

        //observeData()
        initList()

        button_add_task.setOnClickListener {
            recycler_view_task.visibility = View.GONE
            fragment_container.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, AddTaskFragment()).commit()
        }
    }

    private fun initList() {
        taskList = viewModel.passReadTask()
        adapterRepoTask = AdapterRepoTask(this, taskList)
        binding.recyclerViewTask.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTask.apply {
            adapter = adapterRepoTask
        }
        adapterRepoTask.setData(taskList)
    }

    private fun observeData() {
        viewModel.getTasks().observe(this, Observer {
            if(it != null){
                taskList = it as ArrayList<TaskRepository>
//                adapterRepoTask.setData(taskList)
//                adapterRepoTask.notifyDataSetChanged()
            }
        })
    }

    override fun onButtonClicked(task: TaskRepository) {
        taskRepo = task
        viewModel.passTask(task)
        fragment_container.visibility = View.GONE
        recycler_view_task.visibility = View.VISIBLE
    }
}