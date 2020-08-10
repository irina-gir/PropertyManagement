package com.example.propertymanagement.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.propertymanagement.R
import com.example.propertymanagement.ui.home.MainActivity
import com.example.propertymanagement.utils.toolbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.toolbar("Login")

        init()
    }

    private fun init() {
        layout_login.setOnClickListener(this)
        button_login.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.layout_login -> startActivity(Intent(this, RegisterPage::class.java))
            R.id.button_login -> startActivity(Intent(this, MainActivity::class.java))
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