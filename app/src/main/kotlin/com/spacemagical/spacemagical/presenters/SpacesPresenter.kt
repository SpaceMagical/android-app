package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.fragments.SpacesView
import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.schedulers.IScheduler
import com.spacemagical.spacemagical.services.SpaceService

class SpacesPresenter(val view: SpacesView, val scheduler: IScheduler): IPresenter {
    var spaces: List<Space>? = null

    override fun init() {
        view.showLoadingDialog()
        SpaceService.getSpaces()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe(
                {
                    spaces = it
                    view.setSpaces(it)
                },
                {},
                {view.hideLoadingDialog()}
            )
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }

    fun spaceClicked() {

    }
}
