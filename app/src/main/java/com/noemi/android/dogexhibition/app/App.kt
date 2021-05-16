package com.noemi.android.dogexhibition.app

import android.app.Application
import com.noemi.android.dogexhibition.di.AppComponent
import com.noemi.android.dogexhibition.di.AppModule
import com.noemi.android.dogexhibition.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
        appComponent.inject(this)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}