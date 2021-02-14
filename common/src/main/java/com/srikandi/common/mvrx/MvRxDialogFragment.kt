package com.srikandi.common.mvrx

import android.content.Context
import androidx.annotation.LayoutRes
import dagger.android.support.AndroidSupportInjection

abstract class MvRxDialogFragment(
    @LayoutRes contentLayoutId: Int
): BaseMvRxDialogFragment(contentLayoutId) {
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}