package com.example.propertymanagement.ui.tenant

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.models.Tenant
import java.lang.IllegalArgumentException

class TenantViewModel(private val tenantRepository: TenantRepository) : ViewModel() {

    fun getPostTenantObserver() = tenantRepository.repoPostTenant

    val name = ObservableField<String>()
    val email = ObservableField<String>()
    val mobile = ObservableField<String>()

    fun onButtonSaveTenant(){
        var tenant = Tenant(name = name.get().toString(),
        email = email.get().toString(),
        mobile = mobile.get().toString())
        tenantRepository.postTenantResponse(tenant)
    }

}

class TenantViewModelFactory(private val tenantRepository: TenantRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TenantViewModel(tenantRepository) as T

        throw IllegalArgumentException ("It is only creating TenantViewModel object")
    }

}