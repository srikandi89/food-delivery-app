package com.srikandi.homepage.data.mapper

import com.srikandi.common.extensions.orZero
import com.srikandi.homepage.data.remote.response.HomepageGetCategoryListResponse
import com.srikandi.homepage.data.remote.response.HomepageGetFilterListResponse
import com.srikandi.homepage.data.remote.response.HomepageGetImageSlidersResponse
import com.srikandi.homepage.data.remote.response.HomepageGetProductListResponse
import com.srikandi.homepage.domain.model.HomepageCategoryDto
import com.srikandi.homepage.domain.model.HomepageFilterDto
import com.srikandi.homepage.domain.model.HomepageProductDto
import com.srikandi.homepage.domain.model.HomepageProductListDto
import com.srikandi.uikit.imageslider.ImageSliderDto

class HomepageShowcaseDataMapper {
    fun generateImageSlideList(response: HomepageGetImageSlidersResponse): List<ImageSliderDto> {
        return response.data?.map {
            ImageSliderDto(
                title = it.title.orEmpty(),
                subtitle = it.subtitle.orEmpty(),
                imageUrl = it.imageUrl.orEmpty()
            )
        }.orEmpty()
    }

    fun generateProductList(response: HomepageGetProductListResponse): HomepageProductListDto {
        val productList = response.data?.map {
            HomepageProductDto(
                productId = it.productId.orZero(),
                title = it.title.orEmpty(),
                subtitle = it.title.orEmpty(),
                imageUrl = it.imageUrl.orEmpty(),
                price = it.price.orZero(),
                currency = it.currency.orEmpty(),
                weight = it.weight.orZero(),
                length = it.length.orZero()
            )
        }.orEmpty()

        return HomepageProductListDto(productList)
    }

    fun generateFilterList(response: HomepageGetFilterListResponse): List<HomepageFilterDto> {
        return response.data?.map {
            HomepageFilterDto(
                title = it.title.orEmpty()
            )
        }.orEmpty()
    }

    fun generateCategoryList(response: HomepageGetCategoryListResponse): List<HomepageCategoryDto> {
        return response.data?.map {
            HomepageCategoryDto(
                categoryId = it.categoryId.orZero(),
                title = it.title.orEmpty()
            )
        }.orEmpty()
    }
}