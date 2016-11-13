package com.spacemagical.spacemagical.components

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.adapters.UsersAdapter
import com.spacemagical.spacemagical.models.User

class UsersListCard : LinearLayout {
    var container: View? = null

    init {
        container = inflate(context, R.layout.component_users_list, this)
        val usersList = container?.findViewById(R.id.usersList) as RecyclerView?
        usersList?.layoutManager = LinearLayoutManager(context)
    }

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    fun setUsers(users: List<User>) {
        val usersList: RecyclerView? = container?.findViewById(R.id.usersList) as RecyclerView?
        usersList?.adapter = UsersAdapter(context, users)
        usersList?.adapter?.notifyDataSetChanged()
    }
}
