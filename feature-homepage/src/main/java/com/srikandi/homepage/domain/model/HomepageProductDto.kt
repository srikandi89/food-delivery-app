package com.srikandi.homepage.domain.model

data class HomepageProductDto(
    val productId: Long = 0,
    val title: String = "",
    val subtitle: String = "",
    val imageUrl: String = "",
    val price: Double = 0.0,
    val currency: String = "",
    val weight: Double = 0.0,
    val length: Double = 0.0
)