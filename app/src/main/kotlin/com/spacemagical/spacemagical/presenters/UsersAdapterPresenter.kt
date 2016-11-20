package com.spacemagical.spacemagical.presenters

import com.spacemagical.spacemagical.models.User

interface UsersAdapterPresenter {
    fun onUserClicked(user: User)
}
