package com.srikandi.common.networks

import java.lang.Exception

class ServerErrorException(
    private val errorResponse: ServerErrorResponse
) : Exception(errorResponse.message) {
    fun getErrorResponse() = errorResponse
}