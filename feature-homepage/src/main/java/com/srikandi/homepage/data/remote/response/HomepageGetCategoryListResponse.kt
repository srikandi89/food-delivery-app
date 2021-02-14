package com.srikandi.homepage.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomepageGetCategoryListResponse(
    @SerializedName("data")
    val data: List<HomepageCategoryResponse>? = null
)