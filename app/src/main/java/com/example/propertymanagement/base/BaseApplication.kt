package com.example.propertymanagement.base

import android.app.Application
import com.example.propertymanagement.di.AppComponent
import com.example.propertymanagement.di.DaggerAppComponent

class BaseApplication : Application(){

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

    fun getAppComponent(): AppComponent = appComponent


}