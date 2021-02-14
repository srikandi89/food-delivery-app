package com.srikandi.homepage.data.mapper

import com.srikandi.homepage.data.remote.response.HomepageGetImageSlidersResponse
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
}