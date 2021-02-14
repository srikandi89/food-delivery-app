package com.srikandi.homepage.screen.showcase

import android.os.Bundle
import android.util.Log
import android.view.View
import com.airbnb.mvrx.*
import com.srikandi.common.adapters.ScreenPagerAdapter
import com.srikandi.homepage.R
import com.srikandi.homepage.domain.model.HomepageFilterDto
import com.srikandi.homepage.screen.HomepageFragment
import com.srikandi.homepage.screen.cart.HomepageCartFragment
import com.srikandi.homepage.screen.cartlist.HomepageCartlistFragment
import com.srikandi.homepage.screen.productlist.HomepageProductlistFragment
import com.srikandi.uikit.imageslider.ImageSliderDto
import kotlinx.android.synthetic.main.homepage_fragment_showcase.*
import javax.inject.Inject

class HomepageShowcaseFragment : HomepageFragment(R.layout.homepage_fragment_showcase) {
    @Inject
    lateinit var viewModelFactory: HomepageShowcaseViewModel.Factory

    private val viewModel: HomepageShowcaseViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeImageSlider()
        subscribeCartContainer()
        subscribeFilters()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setupTabLayout()
    }

    override fun onDestroyView() {
        homepage_imageslider_showcase.removeViewPagerChangeListener()
        super.onDestroyView()
    }

    private fun setupNavigation() {
        homepage_counterfab_showcase.setOnClickListener {
            HomepageCartFragment.newInstance().also {
                it.show(childFragmentManager, it.tag.orEmpty())
            }
        }
    }

    private fun setupTabLayout() {
        withState(viewModel) { state ->
            ScreenPagerAdapter(childFragmentManager).apply {
                addFragment(HomepageProductlistFragment.newInstance(), "Pizza")
                addFragment(HomepageProductlistFragment.newInstance(), "Sushi")
                addFragment(HomepageProductlistFragment.newInstance(), "Drinks")
            }.also {
                with(homepage_viewpager_showcase) {
                    adapter = it
                    homepage_tablayout_showcase.setupWithViewPager(this)
                    currentItem = state.tabPosition
                }
            }
        }
    }

    private fun subscribeImageSlider() {
        viewModel.selectSubscribe(HomepageShowcaseState::imageSlidesAsync, uniqueOnly()) {
            manageImageSliderState(it)
        }
    }

    private fun subscribeCartContainer() {
        viewModel.selectSubscribe(HomepageShowcaseState::cartContainer, uniqueOnly()) {
            homepage_counterfab_showcase.count = it.size
        }
    }

    private fun subscribeFilters() {
        viewModel.selectSubscribe(HomepageShowcaseState::filtersAsync, uniqueOnly()) {
            manageFilterState(it)
        }
    }

    private fun manageImageSliderState(dataAsync: Async<List<ImageSliderDto>>) {
        when (dataAsync) {
            is Uninitialized -> viewModel.loadImageSlider()
            is Loading -> {
                // todo: create loading state screen
            }
            is Fail -> {
                // todo: create fail state screen
            }
            is Success -> {
                val data = dataAsync.invoke()

                if (data.isNotEmpty()) {
                    homepage_imageslider_showcase.create(data, childFragmentManager)
                } else {
                    // todo: create empty screen
                }
            }
        }
    }

    private fun manageFilterState(dataAsync: Async<List<HomepageFilterDto>>) {
        when(dataAsync) {
            is Uninitialized -> viewModel.loadFiltersList()
            is Loading -> {
                // todo : add loading indicator
            }
            is Fail -> {
                // todo : create fail state screen
            }
            is Success -> {
                val data = dataAsync.invoke()

                if (data.isNotEmpty()) {
                    // todo : populate filters button as a horizontal recyclerview item
                } else {
                    // todo : create empty screen
                }
            }
        }
    }

    override fun invalidate() {}
}