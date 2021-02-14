package com.srikandi.homepage.extensions

import android.widget.Button
import androidx.core.content.ContextCompat
import com.srikandi.homepage.R

fun Button.setActive(selected: Boolean) {
    if (selected) {
        setBackgroundResource(R.drawable.homepage_bg_general_blackroundedborder)
        setTextColor(ContextCompat.getColor(context, R.color.black))
    }
    else {
        setBackgroundResource(R.drawable.homepage_bg_general_greyroundedborder)
        setTextColor(ContextCompat.getColor(context, R.color.grey_500))
    }
}
