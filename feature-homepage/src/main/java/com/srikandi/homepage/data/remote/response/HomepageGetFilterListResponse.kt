package com.srikandi.homepage.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomepageGetFilterListResponse(
    @SerializedName("data")
    val data: List<HomepageFilterResponse>? = null
)