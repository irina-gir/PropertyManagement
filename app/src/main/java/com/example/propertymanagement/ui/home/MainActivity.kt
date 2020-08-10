package com.example.propertymanagement.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.propertymanagement.R
import com.example.propertymanagement.ui.property.ActivityProperties
import com.example.propertymanagement.ui.taskManagement.ToDoListActivity
import com.example.propertymanagement.ui.tenant.ActivityTenants
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar = tool_bar
        toolbar.title = "APEX"
        setSupportActionBar(toolbar)

        init()

    }

    private fun init() {
        //drawer layout
        drawerLayout = drawer_layout
        navView = nav_view
        var headerView = navView.getHeaderView(0)

        val toogle = ActionBarDrawerToggle(this, drawerLayout, tool_bar, 0, 0)
        toogle.syncState()
        navView.setNavigationItemSelectedListener(this)
        headerView.tv_email.text = "jk@gmail.com"

        image_todo.setOnClickListener(this)
        image_properties.setOnClickListener(this)
        image_tenants.setOnClickListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_todo -> startActivity(Intent(this, ToDoListActivity::class.java))
        }
        return true
    }
    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.image_todo -> startActivity(Intent(this, ToDoListActivity::class.java))
            R.id.image_properties -> startActivity(Intent(this, ActivityProperties::class.java))
            R.id.image_tenants -> startActivity(Intent(this, ActivityTenants::class.java))
        }
    }
}