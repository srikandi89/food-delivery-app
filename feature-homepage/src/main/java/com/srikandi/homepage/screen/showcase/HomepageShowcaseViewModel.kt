package com.srikandi.homepage.screen.showcase

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.srikandi.common.mvrx.MvRxViewModel
import com.srikandi.homepage.domain.HomepageUseCaseImpl
import com.srikandi.uikit.imageslider.ImageSliderDto
import io.reactivex.Observable

class HomepageShowcaseViewModel @AssistedInject constructor(
    @Assisted initialState: HomepageShowcaseState,
    private val homepageUseCase: HomepageUseCaseImpl
) : MvRxViewModel<HomepageShowcaseState>(initialState) {

    fun loadImageSlider() {
        // todo: change with real api invocation
        val data = listOf(
            ImageSliderDto("Pizza", "Makaroni Pizza", "https://image.freepik.com/free-photo/chicken-steak-with-lemon-tomato-chili-carrot-white-plate_1150-25887.jpg"),
            ImageSliderDto("Roasted Wings", "Roasted Chicken Wings", "https://image.freepik.com/free-photo/frozen-homemade-round-cutlets_114579-35131.jpg")
        )
        Observable.just(data).execute {
            copy(imageSlidesAsync = it)
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: HomepageShowcaseState): HomepageShowcaseViewModel
    }

    companion object : MvRxViewModelFactory<HomepageShowcaseViewModel, HomepageShowcaseState> {
        @JvmStatic
        override fun create(
            viewModelContext: ViewModelContext,
            state: HomepageShowcaseState
        ): HomepageShowcaseViewModel? {
            val fragment =
                (viewModelContext as FragmentViewModelContext).fragment<HomepageShowcaseFragment>()
            return fragment.viewModelFactory.create(state)
        }
    }
}