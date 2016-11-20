package com.spacemagical.spacemagical.activities

import android.content.Context
import android.content.Intent

import android.databinding.ViewDataBinding
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.databinding.ActivitySpaceDetailBinding
import com.spacemagical.spacemagical.models.Issue
import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.models.User
import com.spacemagical.spacemagical.presenters.SpaceDetailPresenter
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.views.SpaceDetailView

class SpaceDetailActivity : CoverCollapsedActivity(), SpaceDetailView {
    var presenter: SpaceDetailPresenter? = null
    var spaceDetailBinding: ActivitySpaceDetailBinding? = null

    override fun initChildLayout(binding: ViewDataBinding) {
        spaceDetailBinding = binding as ActivitySpaceDetailBinding
        spaceDetailBinding?.usersListCard?.setTitle("People in this space")
        spaceDetailBinding?.issuesListCard?.setTitle("Issues in this space")
        presenter = SpaceDetailPresenter(this, BaseScheduler)
        presenter?.init(intent.getIntExtra("spaceId", 0))
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_space_detail
    }

    override fun getCoverUrl(): String {
        return intent.getStringExtra("imageUrl")
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

    override fun setSpace(space: Space) {
        setTitle(space.name)
        spaceDetailBinding?.space = space
        spaceDetailBinding?.notifyChange()
    }

    override fun setUsers(users: List<User>) {
        spaceDetailBinding?.usersListCard?.setUsers(users, presenter!!)
    }

    override fun setIssues(issues: List<Issue>) {
        spaceDetailBinding?.issuesListCard?.setIssues(issues)
    }

    override fun showUser(user: User) {
        UserDetailActivity.startActivity(this, user)
    }

    companion object {
        fun startActivity(context: Context, space: Space) {
            val intent = Intent(context, SpaceDetailActivity::class.java)
            intent.putExtra("spaceId", space.id)
            intent.putExtra("imageUrl", space.imageUrl)
            context.startActivity(intent)
        }
    }
}
