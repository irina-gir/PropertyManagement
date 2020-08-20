package com.example.propertymanagement.helpers

import android.content.Context
import android.content.SharedPreferences
import com.example.propertymanagement.models.User

object SessionManager{

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    const val FILE_NAME = "auth_file"
    const val KEY_USER_ID = "userId"
    const val KEY_NAME = "name"
    const val KEY_EMAIL = "email"
    const val KEY_PASSWORD = "password"
    const val KEY_TYPE = "type"
    const val KEY_IS_LOGGED_IN = "isLoggedIn"
    const val KEY_TOKEN = "token"

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        editor= sharedPreferences.edit()
    }

    fun saveUser(user: User, token: String){
        editor.putString(KEY_NAME, user.name)
        editor.putString(KEY_USER_ID, user._id)
        editor.putString(KEY_EMAIL, user.email)
        editor.putString(KEY_PASSWORD, user.password)
        editor.putString(KEY_TYPE, user.type)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_TOKEN, token)
        editor.commit()
    }

    fun getUserId(): String?{
        return sharedPreferences.getString(KEY_USER_ID, null)
    }

    fun getUser(): User{
        var userId = sharedPreferences.getString(KEY_USER_ID, null)
        var name = sharedPreferences.getString(KEY_NAME, null)
        var email = sharedPreferences.getString(KEY_EMAIL, null)
        var type = sharedPreferences.getString(KEY_TYPE, null)

        return User(_id = userId, name = name, email = email, type = type)
    }

    fun isLoggedIn(): Boolean{
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout(){
        editor.clear()
        editor.commit()
    }

}