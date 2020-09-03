package com.example.propertymanagement.di

import com.example.propertymanagement.ui.auth.LoginActivity
import com.example.propertymanagement.ui.auth.RegisterLandlordFragment
import com.example.propertymanagement.ui.auth.RegisterPage
import com.example.propertymanagement.ui.auth.RegisterTenant
import com.example.propertymanagement.ui.property.ActivityProperties
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(propertyActivity: ActivityProperties)
    fun inject(registerPage: RegisterPage)
    fun inject(registerLandlordFragment: RegisterLandlordFragment)
    fun inject(registerTenantFragment: RegisterTenant)
    fun inject(loginActivity: LoginActivity)
}