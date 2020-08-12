package com.example.propertymanagement.api

import com.example.propertymanagement.models.LoginResponse
import com.example.propertymanagement.models.PropertyResponse
import com.example.propertymanagement.models.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.propertymanagement.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://apolis-property-management.herokuapp.com/api/"

    private val endPoint:EndPoint by lazy {
        val client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        client.create(EndPoint::class.java)
    }
    fun getApiEndPoint(): EndPoint = endPoint
}

interface EndPoint{
    @POST("auth/register?name&email&password&type")
    fun postRegisterLandlordUser(@Body user: User) : Call<RegisterResponse>

    @POST("auth/register?name&email&password&type")
    fun postRegisterTenantUser(@Body user: User) : Call<RegisterResponse>

    @POST("auth/login?email&password&type")
    fun postLoginUser(@Body user: User) : Call<LoginResponse>

    @POST("property?propertyStatus&mortageInfo&address&city&state&country&purchasePrice&userId&userType&longitude&latitude")
    fun postProperty(@Body property: PropertyResponse) : Call<PropertyResponse>
}