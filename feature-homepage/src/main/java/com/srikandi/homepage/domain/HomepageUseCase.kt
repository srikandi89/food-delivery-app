package com.srikandi.homepage.domain

import com.srikandi.homepage.domain.model.HomepageImageSlideDto
import io.reactivex.Observable

interface HomepageUseCase {

    fun getSliderImages(): Observable<List<HomepageImageSlideDto>>
}