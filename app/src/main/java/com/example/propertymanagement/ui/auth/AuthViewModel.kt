package com.example.propertymanagement.ui.auth

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.propertymanagement.models.LoginResponse
import com.example.propertymanagement.models.RegisterResponse
import com.example.propertymanagement.models.User
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(private var authRepository: AuthRepository) : ViewModel() {

    val repoLogin: MutableLiveData<LoginResponse> by lazy {
        MutableLiveData<LoginResponse>()
    }

    val listAuthRepository: MutableLiveData<Response<RegisterResponse>> by lazy {
        MutableLiveData<Response<RegisterResponse>>()
    }

    fun getAuthRepositoryObserver() = listAuthRepository

    fun getLoginRepositoryObserver() = repoLogin

    val name = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val confirmPassWord = ObservableField<String>()
    val landlordEmail = ObservableField<String>()

    private val passValidate = MutableLiveData<String>()

    fun getPassLiveDataObserver(): MutableLiveData<String> {
        isPassValid()
        return passValidate
    }

    private fun isPassValid() {
        if (password.get().toString() == confirmPassWord.get().toString()) {
            passValidate.postValue(password.get().toString())
        }
    }

    fun onButtonRegisterLandlord() {
        val type = "landlord"
        var user = User(
            name = name.get().toString(),
            email = email.get().toString(),
            password = password.get().toString(),
            type = type
        )
        viewModelScope.launch {
            val response =authRepository.postRegisterLandlordResponse(user)
            listAuthRepository.postValue(response)
        }
    }

    fun onButtonRegisterTenant() {
        val type = "tenant"
        var user = User(
            name = name.get().toString(),
            email = email.get().toString(),
            password = password.get().toString(),
            type = type
        )
        viewModelScope.launch {
            val response = authRepository.postRegisterTenantResponse(user)
            listAuthRepository.postValue(response)
        }
    }

        fun onButtonLogin() {
            var type = "landlord"
            var user = User(
                email = email.get().toString(),
                password = password.get().toString(),
                type = type
            )
            viewModelScope.launch {
                val response = authRepository.postLoginUserResponse(user)
                repoLogin.postValue(response)
            }
        }

}

class AuthViewModelFactory(private val authRepository: AuthRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(authRepository) as T

        throw IllegalArgumentException("It is only creating AuthViewModel object")

    }

}