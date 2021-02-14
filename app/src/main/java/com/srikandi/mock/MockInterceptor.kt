package com.srikandi.mock

import com.google.gson.Gson
import com.srikandi.BuildConfig
import com.srikandi.homepage.data.remote.response.*
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
                uri.endsWith("productlist") -> {
                    gson.toJson(getMockProductList())
                }
                uri.endsWith("filters") -> {
                    gson.toJson(getMockFilterList())
                }
                uri.endsWith("categories") -> {
                    gson.toJson(getMockCategoryList())
                }
                uri.endsWith("deliveryfee") -> {
                    gson.toJson(getMockDeliveryFee())
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
        val slides = listOf(
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

    private fun getMockProductList(): HomepageGetProductListResponse {
        val slides = listOf(
            HomepageProductResponse(
                productId = 1,
                title = "Papperoni Pizza",
                subtitle = "Grilled Papperoni Pizza",
                imageUrl = "https://image.freepik.com/free-photo/chicken-steak-with-lemon-tomato-chili-carrot-white-plate_1150-25887.jpg",
                price = 60.0,
                currency = "USD",
                weight = 3.5,
                length = 12.0
            ),
            HomepageProductResponse(
                productId = 1,
                title = "Baked Cheezy Chicken",
                subtitle = "Hot Cheezy Baked Chicken",
                imageUrl = "https://image.freepik.com/free-photo/frozen-homemade-round-cutlets_114579-35131.jpg",
                price = 45.0,
                currency = "USD",
                weight = 2.3,
                length = 8.4
            ),
            HomepageProductResponse(
                productId = 1,
                title = "Fried Chicken",
                subtitle = "Hot Spicy Fried Chicken",
                imageUrl = "https://image.freepik.com/free-photo/frozen-homemade-round-cutlets_114579-35131.jpg",
                price = 33.0,
                currency = "USD",
                weight = 2.7,
                length = 9.1
            )
        )

        return HomepageGetProductListResponse(slides)
    }

    private fun getMockFilterList(): HomepageGetFilterListResponse {
        val filters = listOf(
            HomepageFilterResponse("Vegan"),
            HomepageFilterResponse("Spicy")
        )

        return HomepageGetFilterListResponse(filters)
    }

    private fun getMockCategoryList(): HomepageGetCategoryListResponse {
        val categories = listOf(
            HomepageCategoryResponse(1, "Pizza"),
            HomepageCategoryResponse(2, "Sushi"),
            HomepageCategoryResponse(3, "Drinks")
        )

        return HomepageGetCategoryListResponse(categories)
    }

    private fun getMockDeliveryFee(): HomepageGetDeliveryFeeResponse {
        return HomepageGetDeliveryFeeResponse(HomepageDeliveryFeeResponse(12.0, "USD"))
    }
}