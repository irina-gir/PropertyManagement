package com.example.propertymanagement.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.databinding.ActivityLoginBinding
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.helpers.toast
import com.example.propertymanagement.ui.home.MainActivity
import com.example.propertymanagement.helpers.toolbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModelAuth: AuthViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModelAuth = ViewModelProvider(this,
            AuthViewModelFactory(AuthRepository(ApiClient.getApiEndPoint())))
            .get(AuthViewModel::class.java)
        binding.viewModel = viewModelAuth

        this.toolbar("Login")

        SessionManager.init(this)
        init()
        observerData()
    }

    private fun init() {
        layout_login.setOnClickListener(this)
        //button_login.setOnClickListener(this)
    }

    private fun observerData() {
        viewModelAuth.getLoginRepositoryObserver().observe(this, Observer {
            if(it != null){
                startActivity(Intent(this, MainActivity::class.java))
                this.toast("Logged in")
            }else{
                this.toast("Something went wrong...")
            }
        })
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.layout_login -> startActivity(Intent(this, RegisterPage::class.java))
            //R.id.button_login -> startActivity(Intent(this, MainActivity::class.java))
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