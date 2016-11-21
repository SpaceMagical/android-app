package com.spacemagical.spacemagical.presenters

import android.content.Context
import com.spacemagical.spacemagical.data.UserPreference
import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.AuthService
import com.spacemagical.spacemagical.services.UserService
import com.spacemagical.spacemagical.views.LoginView

class LoginPresenter(val view: LoginView, val scheduler: IScheduler) : IPresenter {
    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {

    }

    fun attemptLogin(email: String, password: String, userPreference: UserPreference) {
        view.showLoadingDialog()
        AuthService.login(email, password)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                {
                    val token = it
                    UserService.getMe(it)
                        .subscribeOn(scheduler.backgroundThread())
                        .observeOn(scheduler.mainThread())
                        .subscribe(
                            {
                                userPreference.setFromUser(it)
                                view.login(token)
                            },
                            { view.showLoginFailure() },
                            { view.hideLoadingDialog() }
                        )
                },
                {
                    view.showLoginFailure()
                    view.hideLoadingDialog()
                }
            )
    }
}
