package com.example.propertymanagement.ui.property

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagement.api.EndPoint
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.models.Property
import com.example.propertymanagement.models.PropertyResponse
import com.example.propertymanagement.models.RegisterResponse
import com.example.propertymanagement.models.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPropertyRepository(private var endPoint: EndPoint) {

    suspend fun postProperty(property: Property): Response<Property>? {
        return withContext(Dispatchers.IO){
            val call = endPoint.postProperty(property)
            val response = call.execute()
            if(response != null && response.code() in 200 ..399){
                response
            }else
                null
        }
    }

    suspend fun getProperties(): List<Property>?{
        return withContext(Dispatchers.IO){
            try {
                val result = endPoint.getProperties()
                result.data
            }catch (cause: Throwable){
                throw Throwable("Unable to get Properties", cause)
            }
        }
    }
}