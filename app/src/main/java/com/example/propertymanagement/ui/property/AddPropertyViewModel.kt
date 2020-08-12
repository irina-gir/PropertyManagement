package com.example.propertymanagement.ui.property

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.models.PropertyResponse
import java.lang.IllegalArgumentException

class AddPropertyViewModel(private var propertyRepository: AddPropertyRepository ): ViewModel() {

    fun getAddPropertyRepObserver() = propertyRepository.repoAddProperty

    val address = ObservableField<String>()
    val city = ObservableField<String>()
    val state = ObservableField<String>()
    val zipCode = ObservableField<String>()
    val country = ObservableField<String>()
    val price = ObservableField<String>()

    fun onButtonSaveProperty(){
        var userId = SessionManager.getUserId()
        var property = PropertyResponse(address = address.get().toString()
            , city = city.get().toString(),
            state = state.get().toString(),
        country = country.get().toString(),
        purchasePrice = price.get().toString(),
        userId = userId)
        propertyRepository.postProperty(property)
    }

}

class AddPropertyViewModelFactory(private val propertyRepository: AddPropertyRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddPropertyViewModel(propertyRepository) as T

        throw IllegalArgumentException ("It is only creating AddPropertyViewModel object")
    }

}