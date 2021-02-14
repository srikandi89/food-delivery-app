package com.srikandi.homepage.screen.cart

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.parentFragmentViewModel
import com.airbnb.mvrx.withState
import com.srikandi.common.adapters.ScreenPagerAdapter
import com.srikandi.common.mvrx.MvRxDialogFragment
import com.srikandi.homepage.R
import com.srikandi.homepage.screen.cartlist.HomepageCartlistFragment
import com.srikandi.homepage.screen.showcase.HomepageShowcaseViewModel
import kotlinx.android.synthetic.main.homepage_fragment_cart.*

class HomepageCartFragment : MvRxDialogFragment(R.layout.homepage_fragment_cart) {

    private val viewModel: HomepageShowcaseViewModel by parentFragmentViewModel()

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

    companion object {
        @JvmStatic
        fun newInstance() = HomepageCartFragment()
    }

}