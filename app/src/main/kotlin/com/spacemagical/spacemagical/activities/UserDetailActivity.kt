package com.spacemagical.spacemagical.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.databinding.ActivityUserDetailBinding
import com.spacemagical.spacemagical.models.Issue
import com.spacemagical.spacemagical.models.User
import com.spacemagical.spacemagical.presenters.UserDetailPresenter
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.views.UserDetailView
import com.squareup.picasso.Picasso

class UserDetailActivity : AppCompatActivity(), UserDetailView {
var presenter: UserDetailPresenter? = null
    var binding: ActivityUserDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = UserDetailPresenter(this, BaseScheduler)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        init(binding)
    }

    private fun init(binding: ActivityUserDetailBinding?) {
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding?.issuesListCard?.setTitle("Issues")
        val url = intent.getStringExtra("coverUrl")
        Picasso.with(this).load(url).into(binding?.userCover)
        presenter?.init(intent.getIntExtra("userId", 0))
    }

    override fun onResume() {
        super.onResume()
        presenter?.resume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    override fun setUser(user: User) {
        binding?.toolbarLayout?.title = user.name
        binding?.user = user
        binding?.notifyChange()
    }

    override fun setIssues(issues: List<Issue>) {
        binding?.issuesListCard?.setIssues(issues)
    }

    companion object {
        fun startActivity(context: Context, user: User) {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra("userId", user.id)
            intent.putExtra("coverUrl", user.coverUrl)
            context.startActivity(intent)
        }
    }
}
