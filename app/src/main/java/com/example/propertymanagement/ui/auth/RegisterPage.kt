package com.example.propertymanagement.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.propertymanagement.R
import com.example.propertymanagement.utils.toolbar
import kotlinx.android.synthetic.main.activity_register_page.*

class RegisterPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        this.toolbar("Register")

        init()
    }

    private fun init() {
        var adapterFragment = AdapterFragmentRegister(supportFragmentManager)
        view_pager.adapter = adapterFragment
        tab_layout.setupWithViewPager(view_pager)
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