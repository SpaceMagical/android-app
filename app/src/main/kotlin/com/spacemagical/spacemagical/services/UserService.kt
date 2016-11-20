package com.spacemagical.spacemagical.services

import com.spacemagical.spacemagical.models.JobType
import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.models.User
import rx.Observable
import java.util.*

object UserService {
    fun getAll(): Observable<List<User>> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val jobType = JobType(1, "engineer")
            val users = ArrayList<User>()
            val user1 = User(1, "Jack", "https://goo.gl/7n83ui", "https://goo.gl/lA6tBD", jobType)
            val user2 = User(2, "Json", "https://goo.gl/uxQIOC", "https://goo.gl/lA6tBD", jobType)
            users.add(user1)
            users.add(user2)
            it.onNext(users)
            it.onCompleted()
        }
    }

    fun get(id: Int): Observable<User> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val jobType = JobType(1, "engineer")
            val user = User(1, "Jack", "https://goo.gl/7n83ui", "https://goo.gl/lA6tBD", jobType)
            it.onNext(user)
            it.onCompleted()
        }
    }
}
