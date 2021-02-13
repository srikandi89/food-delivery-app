package com.srikandi.homepage.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomepageImageSliderResponse(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("subtitle")
    val subtitle: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null
)