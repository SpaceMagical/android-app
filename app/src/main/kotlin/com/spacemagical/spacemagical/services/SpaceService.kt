package com.spacemagical.spacemagical.services

import com.spacemagical.spacemagical.models.Space
import rx.Observable
import java.util.*

object SpaceService {
    fun getSpaces(): Observable<List<Space>> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val spaces = ArrayList<Space>()
            val space = Space(0, "test")
            spaces.add(space)
            it.onNext(spaces)
            it.onCompleted()
        }
    }
}
