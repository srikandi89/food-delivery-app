package com.srikandi.homepage.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomepageFilterResponse(
    @SerializedName("title")
    val title: String? = null
)