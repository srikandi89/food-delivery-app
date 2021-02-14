package com.srikandi.homepage.di

import com.srikandi.homepage.HomepageActivity
import com.srikandi.homepage.screen.cart.HomepageCartFragment
import com.srikandi.homepage.screen.cartlist.HomepageCartlistFragment
import com.srikandi.homepage.screen.productlist.HomepageProductlistFragment
import com.srikandi.homepage.screen.showcase.HomepageShowcaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomepageBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindHomepageActivity(): HomepageActivity

    @ContributesAndroidInjector
    abstract fun bindHomepageShowcaseFragment(): HomepageShowcaseFragment

    @ContributesAndroidInjector
    abstract fun bindHomepageProductlistFragment(): HomepageProductlistFragment

    @ContributesAndroidInjector
    abstract fun bindHomepageCartFragment(): HomepageCartFragment

    @ContributesAndroidInjector
    abstract fun bindHomepageCartlistFragment(): HomepageCartlistFragment
}