package com.srikandi.homepage.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomepageCategoryResponse(
    @SerializedName("category_id")
    val categoryId: Long? = null,
    @SerializedName("title")
    val title: String? = null
)