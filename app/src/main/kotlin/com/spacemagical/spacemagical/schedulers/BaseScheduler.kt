package com.spacemagical.spacemagical.schedulers

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

object BaseScheduler: IScheduler {
    override fun mainThread() = AndroidSchedulers.mainThread()
    override fun backgroundThread() = Schedulers.newThread()
}
