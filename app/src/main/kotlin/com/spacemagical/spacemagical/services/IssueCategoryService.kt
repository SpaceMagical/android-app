package com.spacemagical.spacemagical.services

import com.spacemagical.spacemagical.models.IssueCategory
import rx.Observable
import java.util.*

object IssueCategoryService {
    fun  getAll(): Observable<List<IssueCategory>> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val categories = ArrayList<IssueCategory>()
            val category1 = IssueCategory(1, "Engineer")
            val category2 = IssueCategory(2, "Designer")
            categories.add(category1)
            categories.add(category2)
            it.onNext(categories)
            it.onCompleted()
        }
    }

    fun get(id: Int): Observable<IssueCategory> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val category = IssueCategory(id, "Engineer")
            it.onNext(category)
            it.onCompleted()
        }
    }
}
