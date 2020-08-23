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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(private var endPoint: EndPoint) {

//    val listAuthRepository: MutableLiveData<Response<RegisterResponse>> by lazy {
//        MutableLiveData<Response<RegisterResponse>>()
//    }

//    val repoLogin: MutableLiveData<LoginResponse> by lazy{
//        MutableLiveData<LoginResponse>()
//    }

//    fun postRegisterLandlordResponse(user: User) {
//        endPoint.postRegisterLandlordUser(user).enqueue(object : Callback<RegisterResponse> {
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                listAuthRepository.postValue(null)
//                Log.d("abc", t.message.toString())
//            }
//
//            override fun onResponse(
//                call: Call<RegisterResponse>,
//                response: Response<RegisterResponse>
//            ) {
//                listAuthRepository.postValue(response)
//                Log.d("abc", response.message())
//            }
//
//        })
//    }

//    fun postRegisterTenantResponse(user: User) {
//        endPoint.postRegisterTenantUser(user).enqueue(object : Callback<RegisterResponse> {
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                listAuthRepository.postValue(null)
//                Log.d("abc", t.message.toString())
//            }
//
//            override fun onResponse(
//                call: Call<RegisterResponse>,
//                response: Response<RegisterResponse>
//            ) {
//                listAuthRepository.postValue(response)
//                Log.d("abc", response.message())
//            }
//
//        })
//    }

    suspend fun postRegisterLandlordResponse(user: User): Response<RegisterResponse>?{
        return withContext(Dispatchers.IO){
            val call = endPoint.postRegisterLandlordUser(user)
            val registerResponse = call.execute()
            Log.d("register", registerResponse.toString())
            if(registerResponse != null && registerResponse.code() in 200..399){
                registerResponse
            }else
                null
        }
    }

    suspend fun postRegisterTenantResponse(user: User) : Response<RegisterResponse>?{
        return withContext(Dispatchers.IO){
            val call = endPoint.postRegisterTenantUser(user)
            val registerResponse = call.execute()
            Log.d("register", registerResponse.toString())
            if(registerResponse != null && registerResponse.code() in 200..399){
                registerResponse
            }else
                null
        }
    }

    suspend fun postLoginUserResponse(user: User) : LoginResponse? {
        return withContext(Dispatchers.IO) {
            val call = endPoint.postLoginUser(user)
            val response = call.execute()
            if (response != null && response.code() in 200..399) {
                SessionManager.saveUser(response.body()!!.user, response.body()!!.token)
                return@withContext response.body()
            } else
                return@withContext null
        }
    }
}