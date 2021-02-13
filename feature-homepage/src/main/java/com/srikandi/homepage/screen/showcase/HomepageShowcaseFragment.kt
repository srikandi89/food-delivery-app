package com.srikandi.homepage.screen.showcase

import android.os.Bundle
import android.util.Log
import android.view.View
import com.airbnb.mvrx.*
import com.bumptech.glide.Glide
import com.srikandi.common.adapters.GeneralRecyclerviewAdapter
import com.srikandi.common.mvrx.MvRxFragment
import com.srikandi.homepage.R
import com.srikandi.homepage.domain.model.HomepageProductDto
import com.srikandi.homepage.domain.model.HomepageProductListDto
import com.srikandi.uikit.imageslider.ImageSliderDto
import kotlinx.android.synthetic.main.homepage_fragment_showcase.*
import kotlinx.android.synthetic.main.homepage_item_showcase_product.view.*
import javax.inject.Inject

class HomepageShowcaseFragment : MvRxFragment(R.layout.homepage_fragment_showcase) {
    @Inject
    lateinit var viewModelFactory: HomepageShowcaseViewModel.Factory

    private val viewModel: HomepageShowcaseViewModel by fragmentViewModel()

    private val productListAdapter = GeneralRecyclerviewAdapter(
        R.layout.homepage_item_showcase_product,
        onBind = ::bindProductListView,
        itemListener = { item, _, _ ->
            // todo: add on item click listener here
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeImageSlider()
        subscribeProductList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    override fun onDestroyView() {
        homepage_imageslider_showcase.removeViewPagerChangeListener()
        super.onDestroyView()
    }

    private fun setupRecyclerView() {
        with(homepage_recyclerview_showcase) {
            itemAnimator = null
            adapter = productListAdapter
        }
    }

    private fun bindProductListView(item: HomepageProductDto, position: Int, view: View) {
        with(view) {
            homepage_textview_showcase_producttitle.text = item.title
            homepage_textview_showcase_productsubtitle.text = item.subtitle
            Glide.with(this@HomepageShowcaseFragment)
                .load(item.imageUrl)
                .centerCrop()
                .into(homepage_imageview_showcase_productitem)
        }
    }

    private fun subscribeImageSlider() {
        viewModel.selectSubscribe(HomepageShowcaseState::imageSlidesAsync, uniqueOnly()) {
            manageImageSliderState(it)
        }
    }

    private fun subscribeProductList() {
        viewModel.selectSubscribe(HomepageShowcaseState::productsAsync, uniqueOnly()) {
            manageProductListState(it)
        }
    }

    private fun manageImageSliderState(dataAsync: Async<List<ImageSliderDto>>) {
        when(dataAsync) {
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

    private fun manageProductListState(dataAsync: Async<HomepageProductListDto>) {
        when(dataAsync) {
            is Uninitialized -> viewModel.loadProductList()
            is Loading -> {
                // todo: create loading state screen
            }
            is Fail -> {
                // todo: create fail state screen
            }
            is Success -> {
                val data = dataAsync.invoke()
                val products = data.products

                if (products.isNotEmpty()) {
                    productListAdapter.setData(products)
                } else {
                    // todo: create empty screen
                }
            }
        }
    }

    override fun invalidate() { }
}