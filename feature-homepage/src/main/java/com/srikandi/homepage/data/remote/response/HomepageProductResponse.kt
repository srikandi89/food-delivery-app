package com.srikandi.homepage.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomepageProductResponse(
    @SerializedName("product_id")
    val productId: Long? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("subtitle")
    val subtitle: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("price")
    val price: Double? = null
)