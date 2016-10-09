package com.spacemagical.spacemagical.schedulers

import rx.Scheduler

interface IScheduler {
    fun mainThread(): Scheduler
    fun backgroundThread(): Scheduler
}
