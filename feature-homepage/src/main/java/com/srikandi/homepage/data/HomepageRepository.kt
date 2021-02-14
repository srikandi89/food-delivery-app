package com.srikandi.homepage.data

import com.srikandi.common.module.BaseRepository
import com.srikandi.homepage.data.remote.HomepageRemoteRepository
import com.srikandi.homepage.data.remote.response.*
import io.reactivex.Observable
import javax.inject.Inject

class HomepageRepository @Inject constructor (
    private val remoteRepository: HomepageRemoteRepository
): BaseRepository() {
    fun getSliderImages(): Observable<HomepageGetImageSlidersResponse> {
        return generateResponse(remoteRepository.getSliderImages())
    }

    fun getProductList(): Observable<HomepageGetProductListResponse> {
        return generateResponse(remoteRepository.getProductList())
    }

    fun getFilterList(): Observable<HomepageGetFilterListResponse> {
        return generateResponse(remoteRepository.getFilterList())
    }

    fun getCategoryList(): Observable<HomepageGetCategoryListResponse> {
        return generateResponse(remoteRepository.getCategoryList())
    }

    fun getDeliveryFee(): Observable<HomepageGetDeliveryFeeResponse> {
        return generateResponse(remoteRepository.getDeliveryFee())
    }
}