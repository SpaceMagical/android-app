package com.spacemagical.spacemagical.activities

import android.content.Context
import android.content.Intent

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.components.UsersListCard
import com.spacemagical.spacemagical.databinding.ActivitySpaceDetailBinding
import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.models.User
import com.spacemagical.spacemagical.presenters.SpaceDetailPresenter
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.views.SpaceDetailView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class SpaceDetailActivity : AppCompatActivity(), SpaceDetailView {
    var presenter: SpaceDetailPresenter? = null
    var binding: ActivitySpaceDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SpaceDetailPresenter(this, BaseScheduler)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_space_detail)
        init(binding)
    }

    private fun init(binding: ActivitySpaceDetailBinding?) {
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding?.usersListCard?.setTitle("People in this space")
        val url = intent.getStringExtra("imageUrl")
        Picasso.with(this).load(url).into(binding?.spaceImage)
        binding?.executePendingBindings()
        presenter?.init(intent.getIntExtra("spaceId", 0))
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

    override fun setSpace(space: Space) {
        binding?.toolbarLayout?.title = space.name
    }

    override fun setUsers(users: List<User>) {
        val card = binding?.root?.findViewById(R.id.usersListCard) as UsersListCard?
        card?.setUsers(users)
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
