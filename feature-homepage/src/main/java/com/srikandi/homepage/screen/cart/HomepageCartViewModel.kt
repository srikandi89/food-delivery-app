package com.srikandi.homepage.screen.cart

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.srikandi.common.mvrx.MvRxViewModel
import com.srikandi.homepage.domain.HomepageUseCaseImpl

class HomepageCartViewModel @AssistedInject constructor(
    @Assisted initialState: HomepageCartState,
    private val homepageUseCase: HomepageUseCaseImpl
) : MvRxViewModel<HomepageCartState>(initialState) {

    fun setTabPosition(position: Int) = setState {
        copy(tabPosition = position)
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: HomepageCartState): HomepageCartViewModel
    }

    companion object : MvRxViewModelFactory<HomepageCartViewModel, HomepageCartState> {
        @JvmStatic
        override fun create(
            viewModelContext: ViewModelContext,
            state: HomepageCartState
        ): HomepageCartViewModel? {
            val fragment =
                (viewModelContext as FragmentViewModelContext).fragment<HomepageCartFragment>()
            return fragment.viewModelFactory.create(state)
        }
    }
}