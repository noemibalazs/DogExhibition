package com.noemi.android.dogexhibition.di

import android.content.SharedPreferences
import com.noemi.android.dogexhibition.pref.PreferencesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Provides
    @Singleton
    fun providePrefRepository(sharedPreferences: SharedPreferences): PreferencesRepository =
       PreferencesRepository(sharedPreferences)

}