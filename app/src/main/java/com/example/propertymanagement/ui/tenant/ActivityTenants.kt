package com.example.propertymanagement.ui.tenant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.databinding.ActivityTenantsBinding
import com.example.propertymanagement.helpers.toolbar
import kotlinx.android.synthetic.main.activity_tenants.*

class ActivityTenants : AppCompatActivity() {

    private lateinit var tenantViewModel: TenantViewModel
    private lateinit var binding: ActivityTenantsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tenants)

        tenantViewModel = ViewModelProvider(this, TenantViewModelFactory(TenantRepository(ApiClient.getApiEndPoint())))
            .get(TenantViewModel::class.java)
        binding.viewModel = tenantViewModel

        this.toolbar("Tenants")

        init()
    }

    private fun init() {
        button_add_tenant.setOnClickListener {
            val fragment = FragmentAddTenant()
            var transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
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