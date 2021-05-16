package com.noemi.android.dogexhibition.di

import com.noemi.android.dogexhibition.landing.MainActivity
import com.noemi.android.dogexhibition.searching.RandomDogExhibitionActivity
import com.noemi.android.dogexhibition.app.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class, PreferencesModule::class])
interface AppComponent {

    fun inject(app: App)

    fun inject(activity: MainActivity)

    fun inject(randomDogExhibitionActivity: RandomDogExhibitionActivity)
}