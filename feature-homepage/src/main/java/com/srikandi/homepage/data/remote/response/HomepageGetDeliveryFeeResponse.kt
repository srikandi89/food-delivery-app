package com.srikandi.homepage.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomepageGetDeliveryFeeResponse(
    @SerializedName("data")
    val data: HomepageDeliveryFeeResponse? = null
)