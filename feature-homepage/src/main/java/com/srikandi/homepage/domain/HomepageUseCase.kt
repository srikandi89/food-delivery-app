package com.srikandi.homepage.domain

import com.srikandi.homepage.domain.model.HomepageCategoryDto
import com.srikandi.homepage.domain.model.HomepageDeliveryFeeDto
import com.srikandi.homepage.domain.model.HomepageFilterDto
import com.srikandi.homepage.domain.model.HomepageProductListDto
import com.srikandi.uikit.imageslider.ImageSliderDto
import io.reactivex.Observable

interface HomepageUseCase {

    fun getSliderImages(): Observable<List<ImageSliderDto>>

    fun getProductList(): Observable<HomepageProductListDto>

    fun getFilterList(): Observable<List<HomepageFilterDto>>

    fun getCategoryList(): Observable<List<HomepageCategoryDto>>

    fun getDeliveryFee(): Observable<HomepageDeliveryFeeDto>
}