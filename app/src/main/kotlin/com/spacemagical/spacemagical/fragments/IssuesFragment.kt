package com.spacemagical.spacemagical.fragments

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.activities.IssueCreateActivity
import com.spacemagical.spacemagical.adapters.IssuesAdapter
import com.spacemagical.spacemagical.databinding.FragmentIssuesBinding
import com.spacemagical.spacemagical.models.Issue
import com.spacemagical.spacemagical.presenters.IssuesPresenter
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.views.IssuesView
import java.util.*

class IssuesFragment : Fragment(), IssuesView {
    var presenter: IssuesPresenter? = null
    var progressDialog: ProgressDialog? = null
    var binding: FragmentIssuesBinding? = null

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = IssuesPresenter(this, BaseScheduler)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false)
        binding?.createIssueButton?.setOnClickListener { this.showIssueCreator() }
        init(binding)

        return binding?.root
    }

    private fun init(binding: FragmentIssuesBinding?) {
        binding?.issueList?.adapter = IssuesAdapter(context, ArrayList<Issue>())
        binding?.issueList?.layoutManager = LinearLayoutManager(activity)
        presenter?.init()
    }

    override fun setIssues(issues: List<Issue>) {
        binding?.issueList?.adapter = IssuesAdapter(context, issues)
        binding?.issueList?.adapter?.notifyDataSetChanged()
    }

    override fun showLoadingDialog() {
        progressDialog = ProgressDialog(activity)
        progressDialog?.setMessage("Loading issues")
        progressDialog?.show()
    }

    override fun hideLoadingDialog() {
        progressDialog?.hide()
        progressDialog = null
    }

    override fun showIssueCreator() {
        IssueCreateActivity.startActivity(activity)
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
