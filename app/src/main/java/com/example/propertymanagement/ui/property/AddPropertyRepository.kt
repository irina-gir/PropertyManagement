package com.example.propertymanagement.ui.property

import androidx.lifecycle.MutableLiveData
import com.example.propertymanagement.api.EndPoint
import com.example.propertymanagement.models.PropertyResponse
import com.example.propertymanagement.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPropertyRepository(private var endPoint: EndPoint) {

    val repoAddProperty: MutableLiveData<Response<PropertyResponse>> by lazy{
        MutableLiveData<Response<PropertyResponse>>()
    }

    fun postProperty(property: PropertyResponse){
        endPoint.postProperty(property).enqueue(object : Callback<PropertyResponse>{
            override fun onFailure(call: Call<PropertyResponse>, t: Throwable) {
                repoAddProperty.postValue(null)
            }

            override fun onResponse(
                call: Call<PropertyResponse>,
                response: Response<PropertyResponse>
            ) {
                repoAddProperty.postValue(response)
            }

        })
    }
}