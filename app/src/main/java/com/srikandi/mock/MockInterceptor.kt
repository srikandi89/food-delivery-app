package com.srikandi.mock

import com.google.gson.Gson
import com.srikandi.BuildConfig
import com.srikandi.homepage.data.remote.response.HomepageGetImageSlidersResponse
import com.srikandi.homepage.data.remote.response.HomepageImageSliderResponse
import okhttp3.*

class MockInterceptor : Interceptor {
    private val gson = Gson()

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            val responseString = when {
                uri.endsWith("imageliders") -> {
                    gson.toJson(getMockImageSliders())
                }
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString).body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes")
        }
    }

    private fun getMockImageSliders(): HomepageGetImageSlidersResponse {
        val slides = listOf<HomepageImageSliderResponse>(
            HomepageImageSliderResponse(
                title = "Papperoni Pizza",
                subtitle = "Grilled Papperoni Pizza",
                imageUrl = "https://image.freepik.com/free-photo/chicken-steak-with-lemon-tomato-chili-carrot-white-plate_1150-25887.jpg"
            ),
            HomepageImageSliderResponse(
                title = "Baked Cheezy Chicken",
                subtitle = "Hot Cheezy Baked Chicken",
                imageUrl = "https://image.freepik.com/free-photo/frozen-homemade-round-cutlets_114579-35131.jpg"
            )
        )

        return HomepageGetImageSlidersResponse(slides)
    }


}