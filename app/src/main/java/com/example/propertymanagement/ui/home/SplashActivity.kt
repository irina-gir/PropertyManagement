package com.example.propertymanagement.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.propertymanagement.R
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.ui.auth.FirstPage

class SplashActivity : AppCompatActivity() {

    private val delayedTime: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()

    }

    private fun init() {
        var handler = Handler()
        handler.postDelayed({
            checkLogin()
        }, delayedTime)
    }

    private fun checkLogin() {
        SessionManager.init(this)
        var intent = if(SessionManager.isLoggedIn()){
                Intent(this, MainActivity::class.java)
            }else {
            Intent(this, FirstPage::class.java)
        }
        startActivity(intent)
        finish()
    }
}