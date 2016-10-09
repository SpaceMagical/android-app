package com.spacemagical.spacemagical.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.presenters.SpacesPresenter
import com.spacemagical.spacemagical.schedulers.BaseScheduler

class SpacesFragment : Fragment(), SpacesView {
    var presenter: SpacesPresenter? = null
    var progressDialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = SpacesPresenter(this, BaseScheduler)

        val rootView = inflater?.inflate(R.layout.fragment_space_list, container, false)

        return rootView
    }

    override fun setSpaces(spaces: List<Space>) {
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
