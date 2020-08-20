package com.example.propertymanagement.ui.taskManagement

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.FragmentAddTaskBinding
import com.example.propertymanagement.helpers.toast
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_add_task.view.*
import kotlinx.android.synthetic.main.fragment_add_task.view.pick_date
import java.text.SimpleDateFormat
import java.util.*


class AddTaskFragment : Fragment(), View.OnClickListener {

    private lateinit var viewTaskModel: TaskViewModel
    private lateinit var binding: FragmentAddTaskBinding
    var calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task, container, false)
        init(binding.root)
        return binding.root
    }

    private fun init(root: View) {
        root.button_save_task.setOnClickListener {
            viewTaskModel.onButtonSaveClicked()
            activity!!.onBackPressed()
        }

        root.pick_date.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewTaskModel = ViewModelProvider(activity!!).get(TaskViewModel::class.java)
        binding.viewModel = viewTaskModel
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(view: View) {
        //val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        when(view.id){
            R.id.pick_date ->{
                val datePickerDialog = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener {
                        datePicker, year, monthOfYear, dayOfMonth ->
                    pick_date.setText(""+ (monthOfYear+1) + "\\"+ dayOfMonth+ "\\" + year)
                }, year, month, day)
                datePickerDialog.show()

            }
        }
    }

}