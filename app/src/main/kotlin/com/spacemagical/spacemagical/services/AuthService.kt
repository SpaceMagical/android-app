package com.spacemagical.spacemagical.services

import rx.Observable

object AuthService {
    fun login(email: String, password: String): Observable<String> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            it.onNext("token")
            it.onCompleted()
        }
    }

    fun check(token: String): Observable<Boolean> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            it.onNext(true)
            it.onCompleted()
        }
    }
}
