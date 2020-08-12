package com.example.propertymanagement.ui.property

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.databinding.ActivityPropertiesBinding
import com.example.propertymanagement.helpers.toolbar

import kotlinx.android.synthetic.main.activity_properties.*

class ActivityProperties : AppCompatActivity(), View.OnClickListener {

    private lateinit var propertyViewModel: AddPropertyViewModel
    private lateinit var binding: ActivityPropertiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_properties)
        propertyViewModel = ViewModelProvider(this,
        AddPropertyViewModelFactory(AddPropertyRepository(ApiClient.getApiEndPoint())))
            .get(AddPropertyViewModel::class.java)

        binding.viewModel = propertyViewModel

        this.toolbar("Properties")

        init()
    }

    private fun init() {
        button_add_property.setOnClickListener(this)
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