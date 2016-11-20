package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.models.Issue
import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.IssueCategoryService
import com.spacemagical.spacemagical.services.IssueService
import com.spacemagical.spacemagical.views.IssueCreateView

class IssueCreatePresenter(val view: IssueCreateView, val scheduler: IScheduler): IPresenter {
    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {

    }

    fun init() {
        loadIssueCategories()
    }

    fun createIssue(issue: Issue) {
        view.showLoadingDialog()
        IssueService.create(issue)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                {},
                {},
                { view.hideLoadingDialog() }
            )
    }

    private fun loadIssueCategories() {
        view.showLoadingDialog()
        IssueCategoryService.getAll()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                { view.setIssueCategories(it) },
                {},
                { view.hideLoadingDialog() }
            )
    }
}
