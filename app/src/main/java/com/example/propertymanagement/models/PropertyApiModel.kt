package com.example.propertymanagement.models

import com.google.gson.annotations.SerializedName

data class PropertyResponse(
    val count: Int,
    val `data`: List<Property>,
    val error: Boolean
)

data class Property(
    val __v: Int? =null,
    val _id: String? =null,
    val address: String? =null,
    val city: String? =null,
    val country: String? =null,
    val image: String? =null,
    val latitude: String? =null,
    val longitude: String? =null,
    val mortageInfo: Boolean? =null,
    val propertyStatus: Boolean? =null,
    val purchasePrice: String? =null,
    val state: String? =null,
    val userId: String? =null,
    val userType: String? =null
)