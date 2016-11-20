package com.spacemagical.spacemagical.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.services.AuthService

class BootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_boot)

        AuthService.check("")
            .subscribeOn(BaseScheduler.backgroundThread())
            .observeOn(BaseScheduler.mainThread())
            .subscribe(
                {
                    if (it) {
                        MainActivity.startActivity(this)
                    } else {
                        LoginActivity.startActivity(this)
                    }
                },
                {},
                { finish() }
            )
    }
}
