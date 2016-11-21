package com.spacemagical.spacemagical.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.databinding.ActivityIssueCreateBinding
import com.spacemagical.spacemagical.models.Issue
import com.spacemagical.spacemagical.models.IssueCategory
import com.spacemagical.spacemagical.models.JobType
import com.spacemagical.spacemagical.models.User
import com.spacemagical.spacemagical.presenters.IssueCreatePresenter
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.views.IssueCreateView

class IssueCreateActivity : AppCompatActivity(), IssueCreateView {
    var binding: ActivityIssueCreateBinding? = null
    var presenter: IssueCreatePresenter? = null
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_issue_create)
        binding?.createButton?.setOnClickListener { createButtonClicked() }
        presenter = IssueCreatePresenter(this, BaseScheduler)
        presenter?.init()
    }

    override fun createButtonClicked() {
        val title = binding?.titleInput?.text.toString()
        val categoryId = binding?.categorySpinner?.selectedItemPosition
        val category = IssueCategory(categoryId!!, "")
        val user = User(1, "Jack", "test@example.com", "", "", JobType(0, ""))
        val issue = Issue(0, category, user)
        presenter?.createIssue(issue)
    }

    override fun setIssueCategories(categories: List<IssueCategory>) {
        val spinner = binding?.categorySpinner
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        categories.forEach { adapter.add(it.name) }
        spinner?.adapter = adapter
    }

    override fun showLoadingDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Creating new issue")
        progressDialog?.show()
    }

    override fun hideLoadingDialog() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, IssueCreateActivity::class.java)
            context.startActivity(intent)
        }
    }
}
