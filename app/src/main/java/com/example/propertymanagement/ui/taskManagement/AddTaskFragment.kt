package com.example.propertymanagement.ui.taskManagement

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propertymanagement.R
import com.example.propertymanagement.models.TaskRepository
import kotlinx.android.synthetic.main.fragment_add_task.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTaskFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var listener: OnFragmentInteraction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_task, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        view.button_save_task.setOnClickListener {
            var priority = view.edit_text_priority.text.toString()
            var summary = view.edit_text_summary.text.toString()
            var description = view.edit_text_description.text.toString()
            var property = view.edit_text_property.text.toString()
            var dueDate = view.edit_text_date.text.toString()
            var assignTo = view.edit_text_assign.text.toString()
            var task = TaskRepository(priority, summary, description, property, dueDate, assignTo)
            listener?.onButtonClicked(task)
        }

    }

    interface OnFragmentInteraction{
        fun onButtonClicked(task: TaskRepository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ToDoListActivity
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddTaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}