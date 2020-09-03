package com.example.propertymanagement.di

import com.example.propertymanagement.api.EndPoint
import com.example.propertymanagement.models.TaskRepository
import com.example.propertymanagement.ui.auth.AuthRepository
import com.example.propertymanagement.ui.property.AddPropertyRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providePropertyRepository(endPoint: EndPoint): AddPropertyRepository{
        return AddPropertyRepository(endPoint)
    }

    @Provides
    fun provideAuthRepository(endPoint: EndPoint) : AuthRepository{
        return AuthRepository(endPoint)
    }

}