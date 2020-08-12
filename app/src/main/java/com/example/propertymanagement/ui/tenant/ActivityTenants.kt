package com.example.propertymanagement.ui.tenant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.propertymanagement.R
import com.example.propertymanagement.helpers.toolbar
import kotlinx.android.synthetic.main.activity_tenants.*

class ActivityTenants : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenants)

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