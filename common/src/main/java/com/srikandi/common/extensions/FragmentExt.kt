package com.srikandi.common.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

inline fun <reified T : Fragment> AppCompatActivity?.addFragment(
    @IdRes layoutResId: Int = 0,
    isAddToBackStack: Boolean = true,
    data: Bundle = Bundle()
) {
    this?.supportFragmentManager?.beginTransaction()?.apply {
        if (isAddToBackStack) addToBackStack(tag<T>())
        add(layoutResId, T::class.java.newInstance().apply {
            arguments = data
        })
        commitAllowingStateLoss()
    }
}

inline fun <reified T : Fragment> AppCompatActivity?.replaceFragment(
    @IdRes layoutResId: Int = 0,
    isAddToBackStack: Boolean = true,
    data: Bundle = Bundle()
) {
    this?.supportFragmentManager?.beginTransaction()?.apply {
        if (isAddToBackStack) addToBackStack(tag<T>())
        replace(layoutResId, T::class.java.newInstance().apply {
            arguments = data
        })
        commitAllowingStateLoss()
    }
}
