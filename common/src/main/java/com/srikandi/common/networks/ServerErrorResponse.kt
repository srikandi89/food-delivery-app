package com.srikandi.common.networks

import com.google.gson.annotations.SerializedName

data class ServerErrorResponse(
    @SerializedName("error_code")
    val errorCode: Int? = null,
    @SerializedName("message")
    val message: String? = null
)