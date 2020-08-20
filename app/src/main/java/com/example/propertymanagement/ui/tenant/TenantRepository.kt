package com.example.propertymanagement.ui.tenant

import androidx.lifecycle.MutableLiveData
import com.example.propertymanagement.api.EndPoint
import com.example.propertymanagement.models.Tenant
import com.example.propertymanagement.models.TenantResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TenantRepository(private var endPoint: EndPoint) {

    val repoPostTenant: MutableLiveData<Response<TenantResponse>> by lazy {
        MutableLiveData<Response<TenantResponse>>()
    }

    fun postTenantResponse(tenant: Tenant){
        endPoint.postTenant(tenant).enqueue(object : Callback<TenantResponse>{
            override fun onFailure(call: Call<TenantResponse>, t: Throwable) {
                repoPostTenant.postValue(null)
            }

            override fun onResponse(
                call: Call<TenantResponse>,
                response: Response<TenantResponse>
            ) {
                repoPostTenant.postValue(response)
            }

        })
    }
}