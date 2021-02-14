package com.srikandi.homepage.screen.cart

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.srikandi.common.adapters.ScreenPagerAdapter
import com.srikandi.homepage.R
import com.srikandi.homepage.screen.HomepageFragment
import com.srikandi.homepage.screen.cartlist.HomepageCartlistFragment
import kotlinx.android.synthetic.main.homepage_fragment_cart.*
import javax.inject.Inject

class HomepageCartFragment : HomepageFragment(R.layout.homepage_fragment_cart) {
    @Inject
    lateinit var viewModelFactory: HomepageCartViewModel.Factory

    private val viewModel: HomepageCartViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabLayout()
    }

    private fun setupTabLayout() {
        withState(viewModel) { state ->
            ScreenPagerAdapter(childFragmentManager).apply {
                addFragment(HomepageCartlistFragment.newInstance(), "Cart")
                addFragment(HomepageCartlistFragment.newInstance(), "Orders")
                addFragment(HomepageCartlistFragment.newInstance(), "Information")
            }.also {
                with(homepage_viewpager_cart) {
                    adapter = it
                    homepage_tablayout_cart.setupWithViewPager(this)
                    currentItem = state.tabPosition
                }
            }
        }
    }

    override fun invalidate() {}

}