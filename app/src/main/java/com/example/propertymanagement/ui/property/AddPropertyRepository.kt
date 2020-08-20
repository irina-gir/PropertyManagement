package com.example.propertymanagement.ui.property

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagement.api.EndPoint
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.models.Property
import com.example.propertymanagement.models.PropertyResponse
import com.example.propertymanagement.models.RegisterResponse
import com.example.propertymanagement.models.TaskRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPropertyRepository(private var endPoint: EndPoint) {

    private var listProperty= ArrayList<Property>()

    val repoAddProperty: MutableLiveData<Response<Property>> by lazy{
        MutableLiveData<Response<Property>>()
    }

    var repoGetProperties = MutableLiveData<List<Property>>()
    fun getPropertyLiveData(): MutableLiveData<List<Property>>{
        getProperties()
        return repoGetProperties
    }

    fun postProperty(property: Property){
        endPoint.postProperty(property).enqueue(object :Callback<Property>{
            override fun onFailure(call: Call<Property>, t: Throwable) {
                Log.d("post", t.message.toString())
                repoAddProperty.postValue(null)
            }

            override fun onResponse(call: Call<Property>, response: Response<Property>) {
                repoAddProperty.postValue(response)
            }

        })
    }

    private fun getProperties(){
        endPoint.getProperties().enqueue(object: Callback<PropertyResponse>{
            override fun onFailure(call: Call<PropertyResponse>, t: Throwable) {
                Log.d("get", t.message.toString())
            }
            override fun onResponse(
                call: Call<PropertyResponse>,
                response: Response<PropertyResponse>
            ) {
                for(property in response.body()!!.data){
                    if(property.userId == SessionManager.getUserId()){
                        listProperty.add(property)
                        repoGetProperties.postValue(listProperty)
                    }
                }
            }
        })
    }
}