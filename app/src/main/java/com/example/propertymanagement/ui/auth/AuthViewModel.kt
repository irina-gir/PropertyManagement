package com.example.propertymanagement.ui.auth

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.models.User

class AuthViewModel(private var authRepository: AuthRepository) : ViewModel(){

    fun getAuthRepositoryObserver() = authRepository.listAuthRepository

    fun getLoginRepositoryObserver() = authRepository.repoLogin

    val name = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val landlordEmail = ObservableField<String>()

    fun onButtonRegisterLandlord(){
        val type = "landlord"
        var user = User(name = name.get().toString(),
        email = email.get().toString(),
        password = password.get().toString(),
        type = type)
        authRepository.postRegisterLandlordResponse(user)
    }

    fun onButtonRegisterTenant(){
        val type = "tenant"
        var user = User(name = name.get().toString(),
        email = email.get().toString(),
            password = password.get().toString(),
            type = type)
        authRepository.postRegisterTenantResponse(user)
    }

    fun onButtonLogin(){
        var type = "landlord"
        var user = User(email = email.get().toString(),
            password = password.get().toString(),
            type = type
        )
        authRepository.postLoginUserResponse(user)
    }
}

class AuthViewModelFactory(private val authRepository: AuthRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(authRepository) as T

        throw IllegalArgumentException("It is only creating AuthViewModel object")

    }

}