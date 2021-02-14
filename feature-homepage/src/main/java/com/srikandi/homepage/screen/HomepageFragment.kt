package com.srikandi.homepage.screen

import android.content.Context
import androidx.annotation.LayoutRes
import com.srikandi.common.mvrx.MvRxFragment

abstract class HomepageFragment(
    @LayoutRes layoutResId: Int
): MvRxFragment(layoutResId) {
    protected var navigator: HomepageNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is HomepageNavigation) {
            navigator = context
        } else {
            throw ClassCastException("interface not attached")
        }
    }

    override fun onDetach() {
        navigator = null
        super.onDetach()
    }

}