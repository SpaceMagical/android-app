package com.spacemagical.spacemagical.views

import com.spacemagical.spacemagical.models.Space

interface SpacesView {
    fun setSpaces(spaces: List<Space>)
    fun showDetail(space: Space)
    fun showLoadingDialog()
    fun hideLoadingDialog()
}
