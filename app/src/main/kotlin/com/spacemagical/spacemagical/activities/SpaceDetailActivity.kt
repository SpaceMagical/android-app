package com.spacemagical.spacemagical.activities

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import com.spacemagical.spacemagical.BR
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.databinding.ActivitySpaceDetailBinding
import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.presenters.SpaceDetailPresenter
import com.spacemagical.spacemagical.views.SpaceDetailView
import com.squareup.picasso.Picasso

class SpaceDetailActivity : AppCompatActivity(), SpaceDetailView {
    var presenter: SpaceDetailPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SpaceDetailPresenter(this)

        var binding: ActivitySpaceDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_space_detail)
        init(binding)
    }

    private fun init(binding: ActivitySpaceDetailBinding) {
        val uri = intent.data
        val space = Space(uri.getQueryParameter("id").toInt(), "Yokohama", "https://static1.squarespace.com/static/558a2e1ce4b00da0be370872/t/55e9ed37e4b0f00132a88b1c/1441394036297/cowork-cairo.jpg?format=2500w")
        binding.setVariable(BR.space, space)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
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

    override fun onBackClicked() {
    }

    object SpaceDetailBinding {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            Picasso.with(imageView.context).load(url).into(imageView)
        }
    }
}