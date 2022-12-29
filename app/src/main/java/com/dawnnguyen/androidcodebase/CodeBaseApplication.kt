package com.dawnnguyen.androidcodebase

import android.app.Application
import com.orhanobut.hawk.BuildConfig
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CodeBaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Hawk.init(applicationContext).build();
    }
}