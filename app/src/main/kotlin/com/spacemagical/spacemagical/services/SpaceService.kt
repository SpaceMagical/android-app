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
            val space1 = Space(1, "Yokohama", "https://static1.squarespace.com/static/558a2e1ce4b00da0be370872/t/55e9ed37e4b0f00132a88b1c/1441394036297/cowork-cairo.jpg?format=2500w")
            val space2 = Space(2, "Shibuya", "http://barcelonanavigator.com/wp-content/uploads/2013/05/betahaus-Barcelona.jpg")
            spaces.add(space1)
            spaces.add(space2)
            it.onNext(spaces)
            it.onCompleted()
        }
    }
}
