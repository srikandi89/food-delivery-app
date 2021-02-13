package com.srikandi.homepage.data

import com.srikandi.common.module.BaseRepository
import com.srikandi.homepage.data.remote.HomepageRemoteRepository
import com.srikandi.homepage.data.remote.response.HomepageGetImageSlidersResponse
import io.reactivex.Observable
import javax.inject.Inject

class HomepageRepository @Inject constructor (
    private val remoteRepository: HomepageRemoteRepository
): BaseRepository() {
    fun getSliderImages(): Observable<HomepageGetImageSlidersResponse> {
        return generateResponse(remoteRepository.getSliderImages())
    }
}