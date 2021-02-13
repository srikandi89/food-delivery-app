package com.srikandi.common.networks

import io.reactivex.Observable
import retrofit2.Response

typealias SuccessCall<T> = Observable<Response<T>>
