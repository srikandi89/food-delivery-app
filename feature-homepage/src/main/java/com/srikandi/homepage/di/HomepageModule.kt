package com.srikandi.homepage.di

import dagger.Module

@Module(includes = [
    HomepageAssistedModule::class,
    HomepageBuilderModule::class
])
class HomepageModule {

}