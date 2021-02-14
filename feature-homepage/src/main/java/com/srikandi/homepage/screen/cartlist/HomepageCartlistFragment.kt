package com.srikandi.homepage.screen.cartlist

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.parentFragmentViewModel
import com.bumptech.glide.Glide
import com.srikandi.common.adapters.GeneralRecyclerviewAdapter
import com.srikandi.common.mvrx.MvRxDialogFragment
import com.srikandi.homepage.R
import com.srikandi.homepage.domain.model.HomepageCartDto
import com.srikandi.homepage.screen.showcase.HomepageShowcaseState
import com.srikandi.homepage.screen.showcase.HomepageShowcaseViewModel
import kotlinx.android.synthetic.main.homepage_fragment_cartlist.*
import kotlinx.android.synthetic.main.homepage_item_cartlist_cartitem.view.*

class HomepageCartlistFragment : MvRxDialogFragment(R.layout.homepage_fragment_cartlist) {
    private val viewModel: HomepageShowcaseViewModel by parentFragmentViewModel()

    private val cartListAdapter = GeneralRecyclerviewAdapter(
        R.layout.homepage_item_cartlist_cartitem,
        onBind = ::bindCartListView,
        itemListener = { _, _, _ ->

        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeCartContainer()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        with(homepage_recyclerview_cartlist) {
            itemAnimator = null
            adapter = cartListAdapter
        }
    }

    private fun subscribeCartContainer() {
        viewModel.selectSubscribe(HomepageShowcaseState::cartContainer) { cartList ->
            calculateTotal(cartList)

            if (cartList.isNotEmpty()) {
                cartListAdapter.setData(cartList)
            } else {
                // todo : handle empty screen here
            }
        }
    }

    private fun calculateTotal(cartList: List<HomepageCartDto>) {
        var total = 0.0
        var currency = ""
        for (cart in cartList) {
            total += (cart.product.price * cart.quantity)
            currency = cart.product.currency
        }

        homepage_textview_cartlist_value.text =
            getString(R.string.homepage_text_showcase_productprice, total.toString(), currency)
    }

    private fun bindCartListView(item: HomepageCartDto, position: Int, view: View) {
        val product = item.product

        val priceInfo = getString(
            R.string.homepage_text_showcase_productprice,
            product.price.toString(),
            product.currency
        )

        val productAndQty = getString(
            R.string.homepage_text_cartlist_productandqty,
            priceInfo,
            item.quantity.toString()
        )

        with(view) {
            Glide.with(this@HomepageCartlistFragment)
                .load(product.imageUrl).centerCrop()
                .into(homepage_imageview_cartlist_imageitem)
            homepage_textview_cartlist_title.text = product.title
            homepage_textview_cartlist_priceinfo.text = productAndQty
            homepage_imageview_cartlist_removeicon.setOnClickListener {

            }
        }
    }

    override fun invalidate() {}

    companion object {
        @JvmStatic
        fun newInstance() = HomepageCartlistFragment()
    }

}