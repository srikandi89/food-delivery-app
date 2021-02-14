package com.srikandi.homepage.domain

import com.srikandi.uikit.imageslider.ImageSliderDto
import io.reactivex.Observable

interface HomepageUseCase {

    fun getSliderImages(): Observable<List<ImageSliderDto>>
}