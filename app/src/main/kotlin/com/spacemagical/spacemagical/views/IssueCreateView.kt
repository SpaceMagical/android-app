package com.spacemagical.spacemagical.views

import com.spacemagical.spacemagical.models.Issue
import com.spacemagical.spacemagical.models.IssueCategory

interface IssueCreateView {
    fun createButtonClicked()
    fun setIssueCategories(categories: List<IssueCategory>)
    fun showLoadingDialog()
    fun hideLoadingDialog()
}
