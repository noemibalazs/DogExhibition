package com.noemi.android.dogexhibition.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var application: Application) {

    @Provides
    @Singleton
    @ForApplication
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideSharedPref(): SharedPreferences =
        application.getSharedPreferences(application.packageName, Context.MODE_PRIVATE)
}