package com.srikandi.homepage.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomepageGetProductListResponse(
    @SerializedName("data")
    val data: List<HomepageProductResponse>? = null
)