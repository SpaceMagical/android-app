package com.spacemagical.spacemagical.fragments

import com.spacemagical.spacemagical.models.Space

interface SpacesView {
    fun setSpaces(spaces: List<Space>)
    fun showDetail(spaceId: Int)
    fun showLoadingDialog()
    fun hideLoadingDialog()
}
