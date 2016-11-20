package com.spacemagical.spacemagical.presenters

import android.util.Log
import com.spacemagical.spacemagical.models.User
import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.IssueService
import com.spacemagical.spacemagical.services.SpaceService
import com.spacemagical.spacemagical.services.UserService
import com.spacemagical.spacemagical.views.SpaceDetailView

class SpaceDetailPresenter(val view: SpaceDetailView, val scheduler: IScheduler) : IPresenter, UsersAdapterPresenter {
    var loadingCount = 0

    fun init(spaceId: Int) {
        loadSpace(spaceId)
        loadUsers()
        loadIssues()
    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {

    }

    override fun onUserClicked(user: User) {
        view.showUser(user)
    }

    private fun loadSpace(id: Int) {
        incrementLoadingCount()
        SpaceService.get(id)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                { view.setSpace(it) },
                {},
                { decrementLoadingCount() }
            )
    }

    private fun loadUsers() {
        incrementLoadingCount()
        UserService.getAll()
            .onBackpressureBuffer()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                { view.setUsers(it) },
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
