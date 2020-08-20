package com.example.propertymanagement.api

import com.example.propertymanagement.models.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

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
    //auth
    @POST("auth/register?name&email&password&type")
    fun postRegisterLandlordUser(@Body user: User) : Call<RegisterResponse>

    @POST("auth/register?name&email&password&type")
    fun postRegisterTenantUser(@Body user: User) : Call<RegisterResponse>

    @POST("auth/login?email&password&type")
    fun postLoginUser(@Body user: User) : Call<LoginResponse>

    //property
    @POST("property?propertyStatus&mortageInfo&address&city&state&country&purchasePrice&userId&userType&longitude&latitude")
    fun postProperty(@Body property: Property) : Call<Property>

    @Multipart
    @POST("upload/property/picture")
    fun postImageProperty(@Part body: MultipartBody.Part) : Call<UploadResponse>

    @GET("property")
    fun getProperties() : Call<PropertyResponse>

    //tenant
    @POST("tenant")
    fun postTenant(@Body tenant: Tenant): Call<TenantResponse>

}