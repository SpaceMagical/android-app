package com.spacemagical.spacemagical.presenters

import android.net.Uri
import android.util.Log
import com.spacemagical.spacemagical.views.SpacesView
import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.SpaceService

class SpacesPresenter(val view: SpacesView, val scheduler: IScheduler): IPresenter, SpaceAdapterPresenter {
    private var spaces: List<Space>? = null

    private fun loadSpaces() {
        view.showLoadingDialog()
        SpaceService.getAll()
            .onBackpressureBuffer()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                {
                    spaces = it
                    view.setSpaces(it)
                },
                {},
                { view.hideLoadingDialog() }
            )
    }

    fun init() {
        loadSpaces()
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }

    override fun onSpaceClicked(space: Space) {
        Log.i("click", space.name)
        view.showDetail(space)
    }
}
