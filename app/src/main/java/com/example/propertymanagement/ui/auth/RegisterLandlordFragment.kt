package com.example.propertymanagement.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.base.BaseApplication
import com.example.propertymanagement.databinding.FragmentRegisterLandlordBinding
import kotlinx.android.synthetic.main.fragment_register_landlord.view.*
import javax.inject.Inject

class RegisterLandlordFragment : Fragment() {

    private lateinit var viewModelAuth: AuthViewModel
    private lateinit var binding: FragmentRegisterLandlordBinding

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_landlord, container, false)

        init(binding.root)
        return binding.root
    }

    private fun init(root: View) {
        root.layout_registered.setOnClickListener {
            startActivity(Intent(activity!!, LoginActivity::class.java))
        }
    }

    override fun onAttach(context: Context) {
        var baseApplication = context.applicationContext as BaseApplication
        baseApplication.getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelAuth = ViewModelProvider(activity!!,
            AuthViewModelFactory(authRepository))
            .get(AuthViewModel::class.java)
        binding.viewModel = viewModelAuth
    }
}