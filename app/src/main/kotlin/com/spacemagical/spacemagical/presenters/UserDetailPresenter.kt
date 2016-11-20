package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.IssueService
import com.spacemagical.spacemagical.services.UserService
import com.spacemagical.spacemagical.views.UserDetailView


class UserDetailPresenter(val view: UserDetailView, val scheduler: IScheduler) : IPresenter {
    var loadingCount = 0

    fun init(userId: Int) {
        loadUser(userId)
        loadIssues()
    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {

    }

    private fun loadUser(id: Int) {
        incrementLoadingCount()
        UserService.get(id)
            .onBackpressureBuffer()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                { view.setUser(it) },
                {},
                { decrementLoadingCount() }
            )
    }

    private fun loadIssues() {
        incrementLoadingCount()
        IssueService.getAll()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                { view.setIssues(it) },
                {},
                { decrementLoadingCount() }
            )
    }

    private fun incrementLoadingCount() {
        if (loadingCount == 0) {
            view.showLoadingDialog()
        }
        ++loadingCount
    }

    private fun decrementLoadingCount() {
        --loadingCount
        if (loadingCount == 0) {
            view.hideLoadingDialog()
        }
    }
}
