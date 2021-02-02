package com.erill.roger.commons

import io.reactivex.Scheduler
import javax.inject.Named

interface CommonsComponent {

    @Named("io")
    fun ioScheduler(): Scheduler

    @Named("main")
    fun schedulerMain(): Scheduler
}