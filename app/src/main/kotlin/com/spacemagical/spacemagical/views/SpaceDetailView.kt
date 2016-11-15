package com.spacemagical.spacemagical.views

import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.models.User

interface SpaceDetailView {
    fun setSpace(space: Space)
    fun setUsers(users: List<User>)
}
