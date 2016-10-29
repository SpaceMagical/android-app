package com.spacemagical.spacemagical.services

import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.models.User
import rx.Observable
import java.util.*

object UserService {
    fun getUsers(): Observable<List<User>> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val users = ArrayList<User>()
            val user1 = User(1, "Jack", "http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg")
            val user2 = User(2, "Json", "http://matchpredictions.in/wp-content/uploads/2015/09/MS-Dhoni-Cricinfo-Yahoo-Profile-Stats-Highlights.jpg")
            users.add(user1)
            users.add(user2)
            it.onNext(users)
            it.onCompleted()
        }
    }
}
