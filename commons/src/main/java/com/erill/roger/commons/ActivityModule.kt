package com.erill.roger.commons

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
abstract class ActivityModule {

    @Binds
    abstract fun activityProvider(activity: AppCompatActivity): Activity

    @Module
    companion object {
        @Provides
        @JvmStatic
        @Named("main")
        fun schedulerMainProvider(): Scheduler {
            return AndroidSchedulers.mainThread()
        }

        @Provides
        @Named("io")
        internal fun schedulerIoProvider(): Scheduler {
            return Schedulers.io()
        }
    }
}