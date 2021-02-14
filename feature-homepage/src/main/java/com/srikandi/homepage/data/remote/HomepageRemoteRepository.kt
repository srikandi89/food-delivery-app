package com.srikandi.homepage.data.remote

import com.srikandi.common.networks.SuccessCall
import com.srikandi.homepage.data.remote.response.*
import javax.inject.Inject

class HomepageRemoteRepository @Inject constructor(
    private val homepageWebService: HomepageWebService
) {
    fun getSliderImages(): SuccessCall<HomepageGetImageSlidersResponse> =
        homepageWebService.getSliderImages()

    fun getProductList(): SuccessCall<HomepageGetProductListResponse> =
        homepageWebService.getProductList()

    fun getFilterList(): SuccessCall<HomepageGetFilterListResponse> =
        homepageWebService.getFilterList()

    fun getCategoryList(): SuccessCall<HomepageGetCategoryListResponse> =
        homepageWebService.getCategoryList()

    fun getDeliveryFee(): SuccessCall<HomepageGetDeliveryFeeResponse> =
        homepageWebService.getDeliveryFee()
}