package com.example.propertymanagement.ui.property

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.propertymanagement.R
import com.example.propertymanagement.utils.toolbar

import kotlinx.android.synthetic.main.activity_properties.*

class ActivityProperties : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_properties)

        this.toolbar("Properties")

        init()
    }

    private fun init() {
        button_add_property.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button_add_property -> startActivity(Intent(this, AddPropertyActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }
}