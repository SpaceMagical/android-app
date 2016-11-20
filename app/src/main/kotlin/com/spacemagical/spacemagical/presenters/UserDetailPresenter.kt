package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.IssueService
import com.spacemagical.spacemagical.services.UserService
import com.spacemagical.spacemagical.views.UserDetailView


class UserDetailPresenter(val view: UserDetailView, val scheduler: IScheduler) : IPresenter {

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
        UserService.get(id)
            .onBackpressureBuffer()
            .observeOn(scheduler.backgroundThread())
            .subscribeOn(scheduler.mainThread())
            .subscribe(
                { view.setUser(it) },
                {},
                {}
            )
    }

    private fun loadIssues() {
        IssueService.getAll()
            .observeOn(scheduler.backgroundThread())
            .subscribeOn(scheduler.mainThread())
            .subscribe(
                { view.setIssues(it) },
                {},
                {}
            )
    }
}
