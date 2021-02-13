package com.srikandi.homepage.data.mapper

import com.srikandi.homepage.data.remote.response.HomepageGetImageSlidersResponse
import com.srikandi.homepage.domain.model.HomepageImageSlideDto

class HomepageShowcaseDataMapper {
    fun generateImageSlideList(response: HomepageGetImageSlidersResponse): List<HomepageImageSlideDto> {
        return response.data?.map {
            HomepageImageSlideDto(
                title = it.title.orEmpty(),
                subtitle = it.subtitle.orEmpty(),
                imageUrl = it.imageUrl.orEmpty()
            )
        }.orEmpty()
    }
}