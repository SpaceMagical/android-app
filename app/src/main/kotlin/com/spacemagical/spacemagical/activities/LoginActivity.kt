package com.spacemagical.spacemagical.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.databinding.ActivityLoginBinding
import com.spacemagical.spacemagical.presenters.LoginPresenter
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.views.LoginView

class LoginActivity : AppCompatActivity(), LoginView {
    var presenter: LoginPresenter? = null
    var binding: ActivityLoginBinding? = null
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        presenter = LoginPresenter(this, BaseScheduler)

        binding?.loginButton?.setOnClickListener {
            val email = binding?.emailInput?.text.toString()
            val password = binding?.passwordInput?.text.toString()
            presenter?.attemptLogin(email, password)
        }
    }

    override fun showLoadingDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Attempt to login")
        progressDialog?.show()
    }

    override fun hideLoadingDialog() {
        progressDialog?.hide()
        progressDialog = null
    }

    override fun login(token: String) {
        MainActivity.startActivity(this)
        finish()
    }

    override fun showLoginFailure() {
        Snackbar.make(binding?.root as View, "Failed to login", Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}
