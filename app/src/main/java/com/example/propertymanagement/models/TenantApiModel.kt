package com.example.propertymanagement.models

data class TenantResponse(
    val `data`: Tenant,
    val error: Boolean,
    val message: String
)

data class Tenant(
    val __v: Int? = null,
    val _id: String? = null,
    val email: String? = null,
    val mobile: String? = null,
    val name: String? = null
)