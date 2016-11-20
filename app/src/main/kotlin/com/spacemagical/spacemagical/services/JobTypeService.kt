package com.spacemagical.spacemagical.services

import com.spacemagical.spacemagical.models.JobType
import rx.Observable
import java.util.*

object JobTypeService {
    fun  getAll(): Observable<List<JobType>> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val jobTypes = ArrayList<JobType>()
            val jobType = JobType(1, "Engineer")
            val jobType2 = JobType(2, "Designer")
            jobTypes.add(jobType)
            jobTypes.add(jobType2)
            it.onNext(jobTypes)
            it.onCompleted()
        }
    }

    fun get(id: Int): Observable<JobType> {
        return Observable.create {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                it.onError(e)
            }

            val jobType = JobType(id, "Engineer")
            it.onNext(jobType)
            it.onCompleted()
        }
    }
}
