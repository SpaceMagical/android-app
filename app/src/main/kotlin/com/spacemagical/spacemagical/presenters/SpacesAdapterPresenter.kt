package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.models.Space

interface SpacesAdapterPresenter {
    fun onSpaceClicked(space: Space)
}
