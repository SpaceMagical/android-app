package com.spacemagical.spacemagical.services

import com.spacemagical.spacemagical.models.Country
import com.spacemagical.spacemagical.models.State
import com.spacemagical.spacemagical.models.Space
import rx.Observable
import java.util.*

object SpaceService {
    fun getAll(): Observable<List<Space>> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val state = State(1, "Tokyo")
            val country = Country(1, "Japan")
            val spaces = ArrayList<Space>()
            val space1 = Space(1, country, state, "", "Yokohama", "https://static1.squarespace.com/static/558a2e1ce4b00da0be370872/t/55e9ed37e4b0f00132a88b1c/1441394036297/cowork-cairo.jpg?format=2500w", 50, "test")
            val space2 = Space(2, country, state, "", "Shibuya", "http://barcelonanavigator.com/wp-content/uploads/2013/05/betahaus-Barcelona.jpg", 50, "test")
            spaces.add(space1)
            spaces.add(space2)
            it.onNext(spaces)
            it.onCompleted()
        }
    }

    fun get(id: Int): Observable<Space> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val state = State(1, "Tokyo")
            val country = Country(1, "Japan")
            val space = Space(1, country, state, "", "Yokohama", "https://static1.squarespace.com/static/558a2e1ce4b00da0be370872/t/55e9ed37e4b0f00132a88b1c/1441394036297/cowork-cairo.jpg?format=2500w", 50, "test")
            it.onNext(space)
            it.onCompleted()
        }
    }
}
