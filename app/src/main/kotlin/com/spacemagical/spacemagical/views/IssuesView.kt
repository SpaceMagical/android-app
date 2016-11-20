package com.spacemagical.spacemagical.views

import com.spacemagical.spacemagical.models.Issue

interface IssuesView {
    fun setIssues(issues: List<Issue>)
    fun showLoadingDialog()
    fun hideLoadingDialog()
}
