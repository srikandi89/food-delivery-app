package com.srikandi.homepage.screen.showcase

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.srikandi.homepage.domain.model.HomepageProductDto
import com.srikandi.homepage.domain.model.HomepageProductListDto
import com.srikandi.uikit.imageslider.ImageSliderDto

data class HomepageShowcaseState(
    val imageSlidesAsync: Async<List<ImageSliderDto>> = Uninitialized,
    val productsAsync: Async<HomepageProductListDto> = Uninitialized,
    val cartContainer: List<HomepageProductDto> = listOf()
): MvRxState