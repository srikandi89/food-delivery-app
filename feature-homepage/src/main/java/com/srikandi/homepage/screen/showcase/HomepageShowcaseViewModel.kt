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
import io.reactivex.schedulers.Schedulers

class HomepageShowcaseViewModel @AssistedInject constructor(
    @Assisted initialState: HomepageShowcaseState,
    private val homepageUseCase: HomepageUseCaseImpl
) : MvRxViewModel<HomepageShowcaseState>(initialState) {
    fun loadImageSlider() {
        homepageUseCase.getSliderImages().subscribeOn(Schedulers.io()).execute {
            copy(imageSlidesAsync = it)
        }
    }

    fun loadProductList() {
        homepageUseCase.getProductList().subscribeOn(Schedulers.io()).execute {
            copy(productsAsync = it)
        }
    }

    fun loadFiltersList() {
        homepageUseCase.getFilterList().subscribeOn(Schedulers.io()).execute {
            copy(filtersAsync = it)
        }
    }

    fun addCartItem(product: HomepageProductDto) = setState {
        val newList = cartContainer.toMutableList().apply {
            add(product)
        }
        copy(cartContainer = newList)
    }

    fun setTabPosition(position: Int) = setState {
        copy(tabPosition = position)
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