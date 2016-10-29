package com.spacemagical.spacemagical.activities

import android.content.Context
import android.content.Intent

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.ImageView
import com.spacemagical.spacemagical.BR
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.adapters.UsersAdapter
import com.spacemagical.spacemagical.databinding.ActivitySpaceDetailBinding
import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.models.User
import com.spacemagical.spacemagical.presenters.SpaceDetailPresenter
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.views.SpaceDetailView
import com.squareup.picasso.Picasso
import java.util.*

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
        val space = when (intent.getIntExtra("spaceId", 0)) {
            1 -> Space(1, "Yokohama", "https://static1.squarespace.com/static/558a2e1ce4b00da0be370872/t/55e9ed37e4b0f00132a88b1c/1441394036297/cowork-cairo.jpg?format=2500w")
            2 -> Space(2, "Shibuya", "http://barcelonanavigator.com/wp-content/uploads/2013/05/betahaus-Barcelona.jpg")
            else -> Space(0, "test", "test")
        }
        binding?.setVariable(BR.space, space)
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding?.usersList?.adapter = UsersAdapter(this, ArrayList<User>())
        binding?.usersList?.layoutManager = LinearLayoutManager(this)
        binding?.toolbarLayout?.title = space.name
        presenter?.init()
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

    override fun setUsers(users: List<User>) {
        binding?.usersList?.adapter = UsersAdapter(this, users)
        binding?.usersList?.adapter?.notifyDataSetChanged()
    }

    companion object {
        fun startActivity(context: Context, space: Space) {
            val intent = Intent(context, SpaceDetailActivity::class.java)
            intent.putExtra("spaceId", space.id)
            context.startActivity(intent)
        }
    }

    object SpaceDetailBinding {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            Picasso.with(imageView.context).load(url).into(imageView)
        }
    }
}
