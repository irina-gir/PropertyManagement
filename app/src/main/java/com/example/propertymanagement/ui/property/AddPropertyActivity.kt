package com.example.propertymanagement.ui.property

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.databinding.ActivityAddPropertyBinding
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.helpers.toolbar

class AddPropertyActivity : AppCompatActivity() {

    private lateinit var propertyViewModel: AddPropertyViewModel
    private lateinit var binding: ActivityAddPropertyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)

        propertyViewModel = ViewModelProvider(this,
            AddPropertyViewModelFactory(AddPropertyRepository(ApiClient.getApiEndPoint())))
            .get(AddPropertyViewModel::class.java)

        binding.viewModel = propertyViewModel
        SessionManager.init(this)

        this.toolbar("Add Property")

        init()
    }

    private fun init() {
        observerData()
    }

    private fun observerData() {
        propertyViewModel.getAddPropertyRepObserver().observe(this, Observer {
            if(it.isSuccessful){
                startActivity(Intent(this, ActivityProperties::class.java))
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