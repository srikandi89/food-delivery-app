package com.srikandi.homepage.screen.cartlist

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.parentFragmentViewModel
import com.bumptech.glide.Glide
import com.srikandi.common.adapters.GeneralRecyclerviewAdapter
import com.srikandi.common.mvrx.MvRxDialogFragment
import com.srikandi.homepage.R
import com.srikandi.homepage.domain.model.HomepageProductDto
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
            if (cartList.isNotEmpty()) {
                cartListAdapter.setData(cartList)
            } else {
                // todo : handle empty screen here
            }
        }
    }



    private fun bindCartListView(item: HomepageProductDto, position: Int, view: View) {
        with(view) {
            Glide.with(this@HomepageCartlistFragment)
                .load(item.imageUrl).centerCrop()
                .into(homepage_imageview_cartlist_imageitem)
            homepage_textview_cartlist_title.text = item.title
            homepage_textview_cartlist_priceinfo.text = item.price.toString()
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