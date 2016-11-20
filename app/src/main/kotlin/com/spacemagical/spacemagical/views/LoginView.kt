package com.spacemagical.spacemagical.views

interface LoginView {
    fun showLoginFailure()
    fun login(token: String)
    fun showLoadingDialog()
    fun hideLoadingDialog()
}
