package com.srikandi.homepage.data.remote

import com.srikandi.common.networks.SuccessCall
import com.srikandi.homepage.data.remote.response.HomepageGetCategoryListResponse
import com.srikandi.homepage.data.remote.response.HomepageGetFilterListResponse
import com.srikandi.homepage.data.remote.response.HomepageGetImageSlidersResponse
import com.srikandi.homepage.data.remote.response.HomepageGetProductListResponse
import retrofit2.http.GET

interface HomepageWebService {
    @GET("/homepage/imageliders")
    fun getSliderImages(): SuccessCall<HomepageGetImageSlidersResponse>

    @GET("/homepage/productlist")
    fun getProductList(): SuccessCall<HomepageGetProductListResponse>

    @GET("/homepage/filters")
    fun getFilterList(): SuccessCall<HomepageGetFilterListResponse>

    @GET("/homepage/categories")
    fun getCategoryList(): SuccessCall<HomepageGetCategoryListResponse>
}