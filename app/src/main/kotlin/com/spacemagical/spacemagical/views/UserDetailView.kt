package com.spacemagical.spacemagical.views

import com.spacemagical.spacemagical.models.Issue
import com.spacemagical.spacemagical.models.User

interface UserDetailView {
    fun setUser(user: User)
    fun setIssues(issues: List<Issue>)
    fun showLoadingDialog()
    fun hideLoadingDialog()
}
