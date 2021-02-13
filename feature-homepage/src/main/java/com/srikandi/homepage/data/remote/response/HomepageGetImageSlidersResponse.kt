package com.srikandi.homepage.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomepageGetImageSlidersResponse(
    @SerializedName("data")
    val data: List<HomepageImageSliderResponse>? = null
)