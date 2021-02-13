package com.srikandi.homepage.data.remote

import com.srikandi.common.networks.SuccessCall
import com.srikandi.homepage.data.remote.response.HomepageGetImageSlidersResponse
import javax.inject.Inject

class HomepageRemoteRepository @Inject constructor(
    private val homepageWebService: HomepageWebService
) {
    fun getSliderImages(): SuccessCall<HomepageGetImageSlidersResponse> =
        homepageWebService.getSliderImages()
}