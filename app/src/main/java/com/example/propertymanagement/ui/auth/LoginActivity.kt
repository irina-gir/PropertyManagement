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
import com.example.propertymanagement.base.BaseApplication
import com.example.propertymanagement.databinding.ActivityLoginBinding
import com.example.propertymanagement.helpers.*
import com.example.propertymanagement.ui.home.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModelAuth: AuthViewModel
    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        var baseApplication = application as BaseApplication
        baseApplication.getAppComponent().inject(this)

        viewModelAuth = ViewModelProvider(this,
            AuthViewModelFactory(authRepository))
            .get(AuthViewModel::class.java)
        binding.viewModel = viewModelAuth

        this.toolbar("Login")

        SessionManager.init(this)
        init()
        observerData()
    }

    private fun init() {
        layout_login.setOnClickListener(this)
        progress_bar.hide()
    }

    private fun observerData() {
        viewModelAuth.getLoginRepositoryObserver().observe(this, Observer {
            if(it != null){
                progress_bar.show()
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