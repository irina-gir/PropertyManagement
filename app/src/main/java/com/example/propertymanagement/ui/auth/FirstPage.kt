package com.example.propertymanagement.ui.auth

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.propertymanagement.R
import kotlinx.android.synthetic.main.activity_first_page.*

class FirstPage : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)

        init()
    }

    private fun init() {
        button_register.setOnClickListener(this)
        button_login.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button_register -> startActivity(Intent(this, RegisterPage::class.java))
            R.id.button_login -> startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}