package com.srikandi.homepage.domain

import com.srikandi.homepage.data.HomepageRepository
import com.srikandi.homepage.data.mapper.HomepageDataMapper.getShowcaseMapper
import com.srikandi.homepage.domain.model.HomepageImageSlideDto
import io.reactivex.Observable

class HomepageUseCaseImpl(
    private val repository: HomepageRepository
) : HomepageUseCase {
    override fun getSliderImages(): Observable<List<HomepageImageSlideDto>> {
        return repository.getSliderImages().map {
            getShowcaseMapper().generateImageSlideList(it)
        }
    }

}