package com.example.propertymanagement.ui.taskManagement

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.FragmentAddTaskBinding
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_add_task.*

class AddTaskFragment : Fragment() {

    private lateinit var viewTaskModel: TaskViewModel
    private lateinit var binding: FragmentAddTaskBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_task, container, false)
        //init(binding)
        return binding.root
    }

    private fun init(binding: FragmentAddTaskBinding) {
        binding.buttonSaveTask.setOnClickListener {
            activity!!.onDetachedFromWindow()
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewTaskModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.viewModel = viewTaskModel
    }
}