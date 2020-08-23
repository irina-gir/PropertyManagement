package com.example.propertymanagement.ui.property

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.models.Property
import com.example.propertymanagement.models.PropertyResponse
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.IllegalArgumentException

class AddPropertyViewModel(private var propertyRepository: AddPropertyRepository) : ViewModel() {

    val repoAddProperty: MutableLiveData<Response<Property>> by lazy {
        MutableLiveData<Response<Property>>()
    }
    val repoGetProperties: MutableLiveData<List<Property>> by lazy {
        MutableLiveData<List<Property>>()
    }

    fun getPropertyLiveData(): MutableLiveData<List<Property>> {
        getPropertiesfromApi()
        return repoGetProperties
    }

    fun getAddPropertyRepObserver() = repoAddProperty

    fun getPropertiesRepObserver() = getPropertyLiveData()

    val address = ObservableField<String>()
    val city = ObservableField<String>()
    val state = ObservableField<String>()
    val zipCode = ObservableField<String>()
    val country = ObservableField<String>()
    val price = ObservableField<String>()

    fun onButtonSaveProperty(location: String) {
        var userId = SessionManager.getUserId()
        var property = Property(
            address = address.get().toString()
            , city = city.get().toString(),
            state = state.get().toString(),
            country = country.get().toString(),
            purchasePrice = price.get().toString(),
            userId = userId!!,
            image = location
        )

        //launch coroutine in viewModelScope
        viewModelScope.launch {
            val response = propertyRepository.postProperty(property)
            repoAddProperty.postValue(response)
        }
    }

    private fun getPropertiesfromApi() {
        viewModelScope.launch {
            val list = propertyRepository.getProperties()
            var listProperty = ArrayList<Property>()
            for(property in list!!){
                if (property!!.userId == SessionManager.getUserId()) {
                    listProperty.add(property)
                    repoGetProperties.postValue(listProperty)
                }
            }
        }
    }
}

class AddPropertyViewModelFactory(private val propertyRepository: AddPropertyRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddPropertyViewModel(propertyRepository) as T

        throw IllegalArgumentException("It is only creating AddPropertyViewModel object")
    }

}