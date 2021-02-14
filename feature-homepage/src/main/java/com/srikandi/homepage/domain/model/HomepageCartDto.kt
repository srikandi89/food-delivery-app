package com.srikandi.homepage.domain.model

data class HomepageCartDto(
    val product: HomepageProductDto = HomepageProductDto(),
    var quantity: Int = 0
)