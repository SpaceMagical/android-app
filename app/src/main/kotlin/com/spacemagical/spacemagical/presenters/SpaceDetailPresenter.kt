package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.SpaceService
import com.spacemagical.spacemagical.services.UserService
import com.spacemagical.spacemagical.views.SpaceDetailView

class SpaceDetailPresenter(val view: SpaceDetailView, val scheduler: IScheduler) : IPresenter {

    fun init(spaceId: Int) {
        loadSpace(spaceId)
        loadUsers()
    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {

    }

    private fun loadSpace(id: Int) {
        SpaceService.get(id)
            .observeOn(scheduler.backgroundThread())
            .subscribeOn(scheduler.mainThread())
            .subscribe(
                { view.setSpace(it) },
                {},
                {}
            )
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
