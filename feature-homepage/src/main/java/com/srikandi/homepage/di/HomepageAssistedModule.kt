package com.srikandi.homepage.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_HomepageAssistedModule::class])
abstract class HomepageAssistedModule