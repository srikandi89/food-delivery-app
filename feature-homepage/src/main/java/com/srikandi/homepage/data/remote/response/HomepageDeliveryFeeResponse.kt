package com.srikandi.homepage.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomepageDeliveryFeeResponse(
    @SerializedName("fee")
    val fee: Double? = null,
    @SerializedName("currency")
    val currency: String? = null
)