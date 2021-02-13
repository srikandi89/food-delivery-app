package com.srikandi.homepage.data.remote

import com.srikandi.common.networks.SuccessCall
import com.srikandi.homepage.data.remote.response.HomepageGetImageSlidersResponse
import retrofit2.http.GET

interface HomepageWebService {
    @GET("/homepage/imagesliders")
    fun getSliderImages(): SuccessCall<HomepageGetImageSlidersResponse>
}