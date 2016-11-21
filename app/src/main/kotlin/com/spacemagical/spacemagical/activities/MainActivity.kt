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
import android.widget.ImageView
import android.widget.TextView
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.data.UserPreference
import com.spacemagical.spacemagical.fragments.IssuesFragment
import com.spacemagical.spacemagical.fragments.SpacesFragment
import com.squareup.picasso.Picasso

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

        val userPreference = UserPreference(this)

        val header = navigation.getHeaderView(0)
        val coverImage = header.findViewById(R.id.coverImage) as ImageView
        Picasso.with(this).load(userPreference.coverUrl).into(coverImage)
        val iconImage = header.findViewById(R.id.iconImage) as ImageView
        Picasso.with(this).load(userPreference.iconUrl).into(iconImage)
        val userName = header.findViewById(R.id.userName) as TextView
        userName.text = userPreference.name
        val userEmail = header.findViewById(R.id.userEmail) as TextView
        userEmail.text = userPreference.email

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
