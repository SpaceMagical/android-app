package com.spacemagical.spacemagical.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.spacemagical.spacemagical.models.User

class UserPreference(context: Context) {
    var prefs: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    var id: Int
        get() = prefs?.getInt("user:id", 0)!!
        set(value) { editor?.putInt("user:id", value) }

    var name: String
        get() = prefs?.getString("user:name", "")!!
        set(value) { editor?.putString("user:name", value) }

    var email: String
        get() = prefs?.getString("user:email", "")!!
        set(value) { editor?.putString("user:email", value) }

    var iconUrl: String
        get() = prefs?.getString("user:iconUrl", "")!!
        set(value) { editor?.putString("user:iconUrl", value) }

    var coverUrl: String
        get() = prefs?.getString("user:coverUrl", "")!!
        set(value) { editor?.putString("user:coverUrl", value) }

    var jobTypeId: Int
        get() = prefs?.getInt("user:jobTypeId", 0)!!
        set(value) { editor?.putInt("user:jobTypeId", value) }

    var token: String
        get() = prefs?.getString("user:token", "")!!
        set(value) { editor?.putString("user:token", value) }

    init {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        editor = prefs?.edit()
    }

    fun setFromUser(user: User) {
        id = user.id
        name = user.name
        email = user.email
        iconUrl = user.iconUrl
        coverUrl = user.coverUrl
        jobTypeId = user.jobType.id
        applyChange()
    }

    fun applyChange() {
        editor?.apply()
    }
}
