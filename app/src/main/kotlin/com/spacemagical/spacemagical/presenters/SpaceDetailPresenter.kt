package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.models.User
import com.spacemagical.spacemagical.schedulers.BaseScheduler
import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.UserService
import com.spacemagical.spacemagical.views.SpaceDetailView

class SpaceDetailPresenter(val view: SpaceDetailView, val scheduler: IScheduler) : IPresenter {

    override fun init() {
        loadUsers()
    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {

    }

    private fun loadUsers() {
        UserService.getAll()
            .onBackpressureBuffer()
            .observeOn(scheduler.backgroundThread())
            .subscribeOn(scheduler.mainThread())
            .subscribe(
                { view.setUsers(it) },
                {},
                {}
            )
    }
}
