package com.spacemagical.spacemagical.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.spacemagical.spacemagical.R

class BootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_boot)

        LoginActivity.startActivity(this)
        finish()
    }
}
