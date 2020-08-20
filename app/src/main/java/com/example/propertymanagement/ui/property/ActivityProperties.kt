package com.example.propertymanagement.ui.property

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagement.R
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.databinding.ActivityPropertiesBinding
import com.example.propertymanagement.helpers.*
import com.example.propertymanagement.models.Property
import com.example.propertymanagement.models.PropertyResponse

import kotlinx.android.synthetic.main.activity_properties.*
import kotlinx.android.synthetic.main.activity_properties.view.*

class ActivityProperties : AppCompatActivity(), View.OnClickListener {

    private lateinit var propertyViewModel: AddPropertyViewModel
    private lateinit var binding: ActivityPropertiesBinding
    private lateinit var adapterProperty: AdapterAddProperty
    private var propertyList = ArrayList<Property>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_properties)
        propertyViewModel = ViewModelProvider(this,
        AddPropertyViewModelFactory(AddPropertyRepository(ApiClient.getApiEndPoint())))
            .get(AddPropertyViewModel::class.java)

        binding.viewModel = propertyViewModel

        this.toolbar("Properties")

        init(binding.root)
        observerData()
    }

    private fun init(root: View) {
        root.progress_bar.show()

        root.button_add_property.setOnClickListener(this)
        adapterProperty = AdapterAddProperty(this)
        binding.recyclerViewProperty.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewProperty.apply {
            adapter = adapterProperty
        }

    }

    private fun observerData() {
        propertyViewModel.getPropertiesRepObserver().observe(this, Observer {
            if(it != null){
                propertyList = it as ArrayList<Property>
                adapterProperty.setData(propertyList)
                progress_bar.hide()
            }
        })
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button_add_property -> startActivity(Intent(this, AddPropertyActivity::class.java))
        }
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