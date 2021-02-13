package com.srikandi.homepage.di

import com.srikandi.homepage.data.HomepageRepository
import com.srikandi.homepage.data.remote.HomepageRemoteRepository
import com.srikandi.homepage.data.remote.HomepageWebService
import com.srikandi.homepage.domain.HomepageUseCaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(
    includes = [
        HomepageAssistedModule::class,
        HomepageBuilderModule::class
    ]
)
class HomepageModule {
    @Provides
    fun provideHomepageRemoteRepository(retrofit: Retrofit): HomepageRemoteRepository {
        val webService = retrofit.create(HomepageWebService::class.java)
        return HomepageRemoteRepository(webService)
    }

    @Provides
    fun provideRepository(
        homepageRemoteRepository: HomepageRemoteRepository
    ) = HomepageRepository(homepageRemoteRepository)

    @Provides
    fun provideUseCase(
        homepageRepository: HomepageRepository
    ) = HomepageUseCaseImpl(homepageRepository)
}