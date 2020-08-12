package com.example.propertymanagement.models

data class RegisterResponse(
    val user: User,
    val error: Boolean,
    val message: String
)
data class LoginResponse(
    val token: String,
    val user: User
)

data class User(
    val __v: Int? = null,
    val _id: String? = null,
    val createdAt: String? = null,
    val email: String? = null,
    val name: String? = null,
    val password: String? = null,
    val type: String? = null
)