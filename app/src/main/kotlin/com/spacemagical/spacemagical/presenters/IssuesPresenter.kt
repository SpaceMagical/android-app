package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.IssueService
import com.spacemagical.spacemagical.views.IssuesView

class IssuesPresenter(val view: IssuesView, val scheduler: IScheduler): IPresenter {

    private fun loadIssues() {
        view.showLoadingDialog()
        IssueService.getAll()
            .onBackpressureBuffer()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                {
                    view.setIssues(it)
                },
                {},
                { view.hideLoadingDialog() }
            )
    }

    fun init() {
        loadIssues()
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }
}
