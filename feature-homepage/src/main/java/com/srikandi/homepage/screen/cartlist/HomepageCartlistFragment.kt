package com.srikandi.homepage.screen.cartlist

import com.srikandi.homepage.R
import com.srikandi.homepage.screen.HomepageFragment

class HomepageCartlistFragment : HomepageFragment(R.layout.homepage_fragment_cartlist) {

    override fun invalidate() { }

    companion object {
        @JvmStatic
        fun newInstance() = HomepageCartlistFragment()
    }

}