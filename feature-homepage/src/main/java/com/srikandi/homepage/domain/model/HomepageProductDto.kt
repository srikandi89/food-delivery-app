package com.srikandi.homepage.domain.model

data class HomepageProductDto(
    val productId: Long = 0,
    val title: String = "",
    val subtitle: String = "",
    val price: Double = 0.0
)