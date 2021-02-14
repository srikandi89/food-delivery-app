package com.srikandi.homepage.screen.showcase

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.srikandi.homepage.domain.model.*
import com.srikandi.uikit.imageslider.ImageSliderDto

data class HomepageShowcaseState(
    val imageSlidesAsync: Async<List<ImageSliderDto>> = Uninitialized,
    val productsAsync: Async<HomepageProductListDto> = Uninitialized,
    val filtersAsync: Async<List<HomepageFilterDto>> = Uninitialized,
    val categoriesAsync: Async<List<HomepageCategoryDto>> = Uninitialized,
    val deliveryFeeAsync: Async<HomepageDeliveryFeeDto> = Uninitialized,
    val cartContainer: List<HomepageCartDto> = listOf(),
    val tabPosition: Int = 0
): MvRxState