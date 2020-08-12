package com.example.propertymanagement.ui.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.api.EndPoint
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.models.LoginResponse
import com.example.propertymanagement.models.RegisterResponse
import com.example.propertymanagement.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(private var endPoint: EndPoint) {

    val listAuthRepository: MutableLiveData<Response<RegisterResponse>> by lazy {
        MutableLiveData<Response<RegisterResponse>>()
    }

    val repoLogin: MutableLiveData<LoginResponse> by lazy{
        MutableLiveData<LoginResponse>()
    }

    fun postRegisterLandlordResponse(user: User) {
        endPoint.postRegisterLandlordUser(user).enqueue(object: Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                listAuthRepository.postValue(null)
                Log.d("abc", t.message.toString())
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                listAuthRepository.postValue(response)
                Log.d("abc", response.message())
            }

        })
    }

    fun postRegisterTenantResponse(user: User){
        endPoint.postRegisterTenantUser(user).enqueue(object : Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                listAuthRepository.postValue(null)
                Log.d("abc", t.message.toString())
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                listAuthRepository.postValue(response)
                Log.d("abc", response.message())
            }

        })
    }

    fun postLoginUserResponse(user: User) {
        endPoint.postLoginUser(user).enqueue(object :Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                repoLogin.postValue(null)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse = response.body()
                SessionManager.saveUser(loginResponse!!.user, loginResponse!!.token)
                repoLogin.postValue(loginResponse)
            }

        })
    }
}