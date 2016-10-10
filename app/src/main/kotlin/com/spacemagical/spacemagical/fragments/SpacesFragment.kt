package com.spacemagical.spacemagical.fragments

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.adapters.SpacesAdapter
import com.spacemagical.spacemagical.databinding.FragmentSpacesBinding
import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.presenters.SpacesPresenter
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import java.util.*

class SpacesFragment : Fragment(), SpacesView {
    var presenter: SpacesPresenter? = null
    var progressDialog: ProgressDialog? = null
    var binding: FragmentSpacesBinding? = null

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = SpacesPresenter(this, BaseScheduler)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_spaces, container, false)

        init(binding)

        return binding?.root
    }

    private fun init(binding: FragmentSpacesBinding?) {
        binding?.spacesList?.adapter = SpacesAdapter(context, ArrayList<Space>(), presenter!!)
        binding?.spacesList?.layoutManager = LinearLayoutManager(activity)
    }

    override fun setSpaces(spaces: List<Space>) {
        binding?.spacesList?.adapter = SpacesAdapter(context, spaces, presenter!!)
        binding?.spacesList?.adapter?.notifyDataSetChanged()
    }

    override fun showDetail(spaceId: Int) {
    }

    override fun showLoadingDialog() {
        progressDialog = ProgressDialog(activity)
        progressDialog?.setMessage("Loading spaces")
        progressDialog?.show()
    }

    override fun hideLoadingDialog() {
        progressDialog?.hide()
        progressDialog = null
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
}
