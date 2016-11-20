package com.spacemagical.spacemagical.services

import com.spacemagical.spacemagical.models.Issue
import com.spacemagical.spacemagical.models.IssueCategory
import com.spacemagical.spacemagical.models.JobType
import com.spacemagical.spacemagical.models.User
import rx.Observable
import java.util.*

object IssueService {
    fun getAll(): Observable<List<Issue>> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }
            val jobType = JobType(1, "engineer")
            val user = User(1, "Jack", "https://goo.gl/7n83ui", "https://goo.gl/lA6tBD", jobType)
            val issueCategory = IssueCategory(1, "Web")
            val issues = ArrayList<Issue>()
            val issue = Issue(1, issueCategory, user)
            val issue2 = Issue(2, issueCategory, user)
            issues.add(issue)
            issues.add(issue2)
            it.onNext(issues)
            it.onCompleted()
        }
    }

    fun get(id: Int): Observable<Issue> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val jobType = JobType(1, "engineer")
            val user = User(1, "Jack", "https://goo.gl/7n83ui", "https://goo.gl/lA6tBD", jobType)
            val issueCategory = IssueCategory(1, "Web")
            val issue = Issue(id, issueCategory, user)
            it.onNext(issue)
            it.onCompleted()
        }
    }

    fun create(issue: Issue): Observable<Issue> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            it.onNext(issue)
            it.onCompleted()
        }
    }
}
