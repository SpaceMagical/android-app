package com.spacemagical.spacemagical.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.databinding.BindingAdapter
import android.databinding.ViewDataBinding
import android.support.annotation.Nullable
import android.view.MenuItem
import android.widget.ImageView
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.databinding.ActivityUserDetailBinding
import com.spacemagical.spacemagical.models.Issue
import com.spacemagical.spacemagical.models.User
import com.spacemagical.spacemagical.presenters.UserDetailPresenter
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.views.UserDetailView
import com.squareup.picasso.Picasso

class UserDetailActivity : CoverCollapsedActivity(), UserDetailView {
    var presenter: UserDetailPresenter? = null
    var userDetailBinding: ActivityUserDetailBinding? = null
    var progressDialog: ProgressDialog? = null

    override fun initChildLayout(binding: ViewDataBinding) {
        userDetailBinding = binding as ActivityUserDetailBinding
        userDetailBinding?.issuesListCard?.setTitle("Issues")
        userDetailBinding?.executePendingBindings()
        presenter = UserDetailPresenter(this, BaseScheduler)
        presenter?.init(intent.getIntExtra("spaceId", 0))
    }

    override fun getCoverUrl(): String {
        return intent.getStringExtra("coverUrl")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_user_detail
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

    override fun setUser(user: User) {
        setTitle(user.name)
        userDetailBinding?.user = user
        userDetailBinding?.notifyChange()
    }

    override fun setIssues(issues: List<Issue>) {
        userDetailBinding?.issuesListCard?.setIssues(issues)
    }

    override fun showLoadingDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.show()
    }

    override fun hideLoadingDialog() {
        progressDialog?.hide()
        progressDialog = null
    }

    object UserDetailActivityBinding {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String?) {
            Picasso.with(imageView.context).load(url).into(imageView)
        }
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
