package com.srikandi.homepage.di

import com.srikandi.homepage.HomepageActivity
import com.srikandi.homepage.screen.showcase.HomepageShowcaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomepageBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindHomepageActivity(): HomepageActivity

    @ContributesAndroidInjector
    abstract fun bindHomepageShowcaseFragment(): HomepageShowcaseFragment
}