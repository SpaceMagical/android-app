package com.spacemagical.spacemagical.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.fragments.IssuesFragment
import com.spacemagical.spacemagical.fragments.SpacesFragment

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private var activityContainer: DrawerLayout? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activityContainer = findViewById(R.id.activity_container) as DrawerLayout

        val navigation = findViewById(R.id.navigation) as NavigationView
        navigation.setNavigationItemSelectedListener(this)
        navigation.setCheckedItem(R.id.spaces)

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, activityContainer, toolbar, R.string.open_drawer, R.string.close_drawer)
        activityContainer?.addDrawerListener(actionBarDrawerToggle!!)

        changeFragmentTo(R.id.spaces)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        if (!menuItem.isChecked) {
            menuItem.isChecked = !menuItem.isChecked

            changeFragmentTo(menuItem.itemId)
        }
        activityContainer?.closeDrawers()
        return true
    }

    private fun changeFragmentTo(id: Int) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.activity_content, when (id) {
            R.id.spaces -> SpacesFragment()
            R.id.issues -> IssuesFragment()
            else -> null
        })
        fragmentTransaction.commit()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle?.onConfigurationChanged(newConfig)
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
