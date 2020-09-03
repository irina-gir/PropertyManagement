package com.example.propertymanagement.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.base.BaseApplication
import com.example.propertymanagement.databinding.ActivityRegisterPageBinding
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.helpers.toast
import com.example.propertymanagement.helpers.toolbar
import com.example.propertymanagement.ui.home.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register_page.*
import javax.inject.Inject

class RegisterPage : AppCompatActivity() {

    private lateinit var viewModelAuth: AuthViewModel
    private lateinit var binding: ActivityRegisterPageBinding

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register_page)

        var baseApplication = application as BaseApplication
        baseApplication.getAppComponent().inject(this)

        viewModelAuth = ViewModelProvider(this,
            AuthViewModelFactory(authRepository))
            .get(AuthViewModel::class.java)
        binding.viewModel = viewModelAuth

        this.toolbar("Register")

        init()
    }

    private fun init() {
        var adapterFragment = AdapterFragmentRegister(supportFragmentManager)
        view_pager.adapter = adapterFragment
        tab_layout.setupWithViewPager(view_pager)
        observerData()
    }

    private fun observerData() {

        viewModelAuth.getAuthRepositoryObserver().observe(this, Observer {
            if(it!!.isSuccessful){
                var intentLogin = Intent(this, LoginActivity::class.java)
                startActivity(intentLogin)
                toast("Registered")
            }
            else{
                this.toast("Something went wrong...")
            }
        })

        viewModelAuth.getPassLiveDataObserver().observe(this, Observer {
            if(it == null){
                toast("Password does not match...")
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