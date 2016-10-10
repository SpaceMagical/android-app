package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.models.Space

interface SpaceAdapterPresenter {
    fun onSpaceClicked(space: Space)
}
