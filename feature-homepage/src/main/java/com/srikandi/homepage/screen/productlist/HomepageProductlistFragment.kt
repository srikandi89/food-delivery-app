package com.srikandi.homepage.screen.productlist

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.*
import com.bumptech.glide.Glide
import com.srikandi.common.adapters.GeneralRecyclerviewAdapter
import com.srikandi.homepage.R
import com.srikandi.homepage.domain.model.HomepageProductDto
import com.srikandi.homepage.domain.model.HomepageProductListDto
import com.srikandi.homepage.screen.HomepageFragment
import com.srikandi.homepage.screen.showcase.HomepageShowcaseState
import com.srikandi.homepage.screen.showcase.HomepageShowcaseViewModel
import kotlinx.android.synthetic.main.homepage_fragment_productlist.*
import kotlinx.android.synthetic.main.homepage_item_showcase_product.view.*

class HomepageProductlistFragment : HomepageFragment(R.layout.homepage_fragment_productlist) {

    private val viewModel: HomepageShowcaseViewModel by parentFragmentViewModel()

    private val productListAdapter = GeneralRecyclerviewAdapter(
        layoutResId = R.layout.homepage_item_showcase_product,
        onBind = ::bindProductListView,
        itemListener = { item, _, _ ->
            // todo : open product detail here
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeProductList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(homepage_recyclerview_productlist) {
            itemAnimator = null
            adapter = productListAdapter
        }
    }

    private fun bindProductListView(item: HomepageProductDto, position: Int, view: View) {
        with(view) {
            homepage_textview_showcase_producttitle.text = item.title
            homepage_textview_showcase_productsubtitle.text = item.subtitle
            homepage_textview_showcase_productdetail.text = getString(
                R.string.homepage_text_showcase_productdetail,
                item.weight.toString(),
                item.length.toString()
            )
            with(homepage_button_showcase_addproduct) {
                text = getString(
                    R.string.homepage_text_showcase_productprice,
                    item.price.toString(),
                    item.currency
                )
                setOnClickListener {
                    viewModel.addCartItem(item)
                }
            }
            Glide.with(this@HomepageProductlistFragment)
                .load(item.imageUrl)
                .centerCrop()
                .into(homepage_imageview_showcase_productitem)
        }
    }

    private fun subscribeProductList() {
        viewModel.selectSubscribe(HomepageShowcaseState::productsAsync, uniqueOnly()) {
            manageProductListState(it)
        }
    }

    private fun manageProductListState(dataAsync: Async<HomepageProductListDto>) {
        when (dataAsync) {
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

    override fun invalidate() {}

    companion object {
        @JvmStatic
        fun newInstance() = HomepageProductlistFragment()
    }

}