package com.example.propertymanagement.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.app_bar.*

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.toolbar(title: String){
    var toolbar = this.tool_bar
    toolbar.title = title
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
}