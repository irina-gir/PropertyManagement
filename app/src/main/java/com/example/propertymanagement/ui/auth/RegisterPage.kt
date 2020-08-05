package com.example.propertymanagement.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.propertymanagement.R
import kotlinx.android.synthetic.main.activity_register_page.*

class RegisterPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        init()
    }

    private fun init() {
        var adapterFragment = AdapterFragmentRegister(supportFragmentManager)
        view_pager.adapter = adapterFragment
        tab_layout.setupWithViewPager(view_pager)
    }
}