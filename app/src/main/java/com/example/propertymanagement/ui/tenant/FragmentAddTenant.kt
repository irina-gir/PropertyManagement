package com.example.propertymanagement.ui.tenant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.databinding.FragmentAddTenantBinding
import kotlinx.android.synthetic.main.activity_tenants.*
import kotlinx.android.synthetic.main.fragment_add_tenant.*


class FragmentAddTenant : Fragment() {

    private lateinit var tenantViewModel: TenantViewModel
    private lateinit var binding: FragmentAddTenantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_tenant, container, false)

        init(binding.root)

        return binding.root
    }

    private fun init(root: View) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        tenantViewModel = ViewModelProvider(this, TenantViewModelFactory(TenantRepository(ApiClient.getApiEndPoint())))
//            .get(TenantViewModel::class.java)
        binding.viewModel = tenantViewModel

        tenantViewModel.getPostTenantObserver().observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful){
                //close the fragment
            }
        })
    }

}