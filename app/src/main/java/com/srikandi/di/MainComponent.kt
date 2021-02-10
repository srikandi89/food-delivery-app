package com.srikandi.di

import android.content.Context
import com.srikandi.MainApplication
import com.srikandi.homepage.di.HomepageModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    MainModule::class,
    HomepageModule::class
])
interface MainComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): MainComponent
    }

    fun inject(app: MainApplication)

    override fun inject(instance: DaggerApplication?)
}