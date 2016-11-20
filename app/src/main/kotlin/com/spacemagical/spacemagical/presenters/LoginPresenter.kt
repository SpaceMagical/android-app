package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.AuthService
import com.spacemagical.spacemagical.views.LoginView

class LoginPresenter(val view: LoginView, val scheduler: IScheduler) : IPresenter {
    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {

    }

    fun attemptLogin(email: String, password: String) {
        view.showLoadingDialog()
        AuthService.login(email, password)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                { view.login(it) },
                { view.showLoginFailure() },
                { view.hideLoadingDialog() }
            )
    }
}
