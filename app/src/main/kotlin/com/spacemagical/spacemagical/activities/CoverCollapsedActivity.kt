package com.spacemagical.spacemagical.activities

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.databinding.ActivityCoverCollapsedBinding
import com.squareup.picasso.Picasso

abstract class CoverCollapsedActivity : AppCompatActivity() {
    var binding: ActivityCoverCollapsedBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cover_collapsed)
        init(binding)
    }

    private fun init(binding: ActivityCoverCollapsedBinding?) {
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        Picasso.with(this).load(getCoverUrl()).into(binding?.coverImage)
        setChildLayout(getLayoutId())
    }

    private fun setChildLayout(layoutId: Int) {
        val container = binding?.container
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(this), layoutId, container, false)
        container?.addView(binding.root)
        initChildLayout(binding)
    }

    protected fun setTitle(title: String) {
        binding?.toolbarLayout?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    abstract protected fun getCoverUrl(): String
    abstract protected fun getLayoutId(): Int
    abstract protected fun initChildLayout(binding: ViewDataBinding)
}
