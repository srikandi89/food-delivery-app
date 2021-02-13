package com.srikandi.homepage.screen

import android.util.Log
import com.srikandi.common.mvrx.MvRxFragment
import com.srikandi.homepage.R
import com.srikandi.uikit.imageslider.ImageSliderDto
import kotlinx.android.synthetic.main.homepage_fragment_showcase.*

class HomepageShowcaseFragment : MvRxFragment(R.layout.homepage_fragment_showcase) {

    override fun onDestroyView() {
        homepage_imageslider_showcase.removeViewPagerChangeListener()
        super.onDestroyView()
    }

    override fun invalidate() {
        manageImageSlider(listOf(
            ImageSliderDto("Pizza", "Makaroni Pizza", "https://image.freepik.com/free-photo/chicken-steak-with-lemon-tomato-chili-carrot-white-plate_1150-25887.jpg"),
            ImageSliderDto("Roasted Wings", "Roasted Chicken Wings", "https://image.freepik.com/free-photo/frozen-homemade-round-cutlets_114579-35131.jpg")
        ))
    }

    private fun manageImageSlider(slides: List<ImageSliderDto>) {
        homepage_imageslider_showcase.create(slides, childFragmentManager)
        Log.d("HOMEPAGE_SHOWCASE", "Count ${homepage_imageslider_showcase.childCount}")
    }

}