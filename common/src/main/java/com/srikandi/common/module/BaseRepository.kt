package com.srikandi.common.module

import com.google.gson.Gson
import com.srikandi.common.networks.ServerErrorException
import com.srikandi.common.networks.ServerErrorResponse
import com.srikandi.common.networks.SuccessCall
import io.reactivex.Observable

open class BaseRepository {
    private val gson = Gson()

    protected fun <T> generateResponse(response: SuccessCall<T>): Observable<T> {
        return response.flatMap {
            val data = it.body()

            return@flatMap if (it.isSuccessful && data != null) {
                Observable.just(data)
            } else createErrorResponse(it.errorBody()?.string().orEmpty())
        }
    }

    private fun <T> createErrorResponse(jsonPayload: String): Observable<T> {
        val errorResponse = gson.fromJson(jsonPayload, ServerErrorResponse::class.java)
        return Observable.error(ServerErrorException(errorResponse))
    }
}