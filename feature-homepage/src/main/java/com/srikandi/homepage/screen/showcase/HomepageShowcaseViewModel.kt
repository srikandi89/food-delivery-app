package com.srikandi.homepage.screen.showcase

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.srikandi.common.mvrx.MvRxViewModel
import com.srikandi.homepage.domain.HomepageUseCaseImpl
import com.srikandi.homepage.domain.model.HomepageCartDto
import com.srikandi.homepage.domain.model.HomepageFilterDto
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

    fun loadCategoryList() {
        homepageUseCase.getCategoryList().subscribeOn(Schedulers.io()).execute {
            copy(categoriesAsync = it)
        }
    }

    fun loadDeliveryFee() {
        homepageUseCase.getDeliveryFee().subscribeOn(Schedulers.io()).execute {
            copy(deliveryFeeAsync = it)
        }
    }

    fun addCartItem(product: HomepageProductDto) = setState {
        val newList = cartContainer.toMutableList().apply {
            val productInCart = find { it.product === product }
            if (productInCart == null) {
                add(HomepageCartDto(product, 1))
            } else {
                productInCart.quantity++
            }
        }
        copy(cartContainer = newList)
    }

    /**
     * for the sake of simplicity, removing the product with quantity > 1
     * will definitely remove its product from the cart
     */
    fun removeCartItem(product: HomepageProductDto) = setState {
        val newList = cartContainer.toMutableList().apply {
            val productInCart = find { it.product === product }
            if (productInCart != null) {
                remove(productInCart)
            }
        }
        copy(cartContainer = newList)
    }

    fun updateSelectedFilters(position: Int) = setState {
        val filters = filtersAsync.invoke().orEmpty().mapIndexed { index, homepageFilterDto ->
            if (index == position) {
                homepageFilterDto.copy(selected = homepageFilterDto.selected.not())
            } else {
                homepageFilterDto.copy()
            }
        }

        copy(filtersAsync = Success(filters))
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