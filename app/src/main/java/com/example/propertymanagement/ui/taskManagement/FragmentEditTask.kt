package com.example.propertymanagement.ui.taskManagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.FragmentEditTaskBinding
import kotlinx.android.synthetic.main.activity_to_do_list.*
import kotlinx.android.synthetic.main.fragment_edit_task.view.*

class FragmentEditTask : Fragment() {

    private lateinit var viewTaskModel: TaskViewModel
    private lateinit var binding: FragmentEditTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_task, container, false)
        init(binding.root)
        return binding.root
    }

    private fun init(root: View) {
        root.button_save_task.setOnClickListener {
            viewTaskModel.onButtonEditSaveClicked()
            activity!!.onBackPressed()
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewTaskModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.viewModel = viewTaskModel
    }
}