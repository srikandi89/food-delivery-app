package com.srikandi.homepage.screen.showcase

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.srikandi.common.mvrx.MvRxViewModel
import com.srikandi.homepage.domain.HomepageUseCaseImpl
import com.srikandi.homepage.domain.model.HomepageProductDto
import com.srikandi.homepage.domain.model.HomepageProductListDto
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

    fun loadProductList() {
        // todo: change with real api invocation
        val data = HomepageProductListDto(
            products = listOf(
                HomepageProductDto(1, "Product 1", "Product Description 1", "https://image.freepik.com/free-photo/chicken-steak-with-lemon-tomato-chili-carrot-white-plate_1150-25887.jpg", 60.0),
                HomepageProductDto(2, "Product 2", "Product Description 2", "https://image.freepik.com/free-photo/frozen-homemade-round-cutlets_114579-35131.jpg", 60.0),
                HomepageProductDto(3, "Product 3", "Product Description 3", "https://image.freepik.com/free-photo/chicken-steak-with-lemon-tomato-chili-carrot-white-plate_1150-25887.jpg", 60.0),
                HomepageProductDto(4, "Product 4", "Product Description 4", "https://image.freepik.com/free-photo/frozen-homemade-round-cutlets_114579-35131.jpg", 60.0),
                HomepageProductDto(5, "Product 5", "Product Description 5", "https://https://image.freepik.com/free-photo/chicken-steak-with-lemon-tomato-chili-carrot-white-plate_1150-25887.jpg", 60.0)
            )
        )
        Observable.just(data).execute {
            copy(productsAsync = it)
        }
    }

    fun addCartItem(product: HomepageProductDto) = setState {
        val newList = cartContainer.toMutableList().apply {
            add(product)
        }
        copy(cartContainer = newList)
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