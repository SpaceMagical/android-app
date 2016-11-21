package com.spacemagical.spacemagical.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.data.UserPreference
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.services.AuthService
import com.spacemagical.spacemagical.services.UserService

class BootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_boot)

        val userPreference = UserPreference(this)

        val token = userPreference.token

        AuthService.check(token)
            .subscribeOn(BaseScheduler.backgroundThread())
            .observeOn(BaseScheduler.mainThread())
            .subscribe(
                {
                    if (it) {
                        UserService.getMe(token)
                            .subscribeOn(BaseScheduler.backgroundThread())
                            .observeOn(BaseScheduler.mainThread())
                            .subscribe(
                                {
                                    userPreference.setFromUser(it)
                                    MainActivity.startActivity(this)
                                },
                                {},
                                { finish() }
                            )
                    } else {
                        LoginActivity.startActivity(this)
                        finish()
                    }
                },
                {},
                {}
            )
    }
}
