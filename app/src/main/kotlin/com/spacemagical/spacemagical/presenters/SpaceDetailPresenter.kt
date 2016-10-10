package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.views.SpaceDetailView

class SpaceDetailPresenter : IPresenter {
    var view: SpaceDetailView? = null

    constructor(view: SpaceDetailView) {
        this.view = view
    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {

    }
}
