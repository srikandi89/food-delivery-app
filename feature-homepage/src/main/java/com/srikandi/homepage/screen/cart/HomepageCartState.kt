package com.srikandi.homepage.screen.cart

import com.airbnb.mvrx.MvRxState
import com.srikandi.homepage.domain.model.HomepageProductDto

data class HomepageCartState(
    val cartContainer: List<HomepageProductDto> = listOf(),
    val tabPosition: Int = 0
): MvRxState