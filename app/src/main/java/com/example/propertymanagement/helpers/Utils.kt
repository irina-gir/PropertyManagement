package com.example.propertymanagement.helpers

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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

fun ProgressBar.show(){
    this.visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    this.visibility = View.GONE
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(image: String?){
    Glide.with(this)
        .load(image)
        .into(this)
}
