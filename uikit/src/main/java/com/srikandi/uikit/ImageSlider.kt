package com.srikandi.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.srikandi.common.adapters.ScreenPagerAdapter
import com.srikandi.uikit.imageslider.ImageSliderDto
import com.srikandi.uikit.imageslider.ImageSliderFragment
import kotlinx.android.synthetic.main.image_slider_layout.view.*

class ImageSlider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var visibleSlidePosition = 0
    private val pageIndicator by lazy {
        imageslider_indicator.apply {
            setViewPager(imageslider_viewpager)
        }
    }
    private val slideChangeListener by lazy {
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                // TODO : do something when state changed
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // TODO : do something when page being scrolled
            }

            override fun onPageSelected(position: Int) {
                pageIndicator.animatePageSelected(position)
            }

        }
    }

    init {
        LayoutInflater.from(context).inflate(
            R.layout.image_slider_layout,
            this,
            true
        )

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ImageSlider)

            for (i in 0 until typedArray.indexCount) {
                when (val attr = typedArray.getIndex(i)) {
                    R.styleable.ImageSlider_show_page_indicator -> {
                        val isShow = typedArray.getBoolean(attr, false)
                        imageslider_indicator.isGone = !isShow
                    }
                }
            }

            typedArray.recycle()
        }
    }

    private fun setupViewPager(screenAdapter: ScreenPagerAdapter) {
        with(imageslider_viewpager) {
            adapter = screenAdapter
            currentItem = visibleSlidePosition
            addOnPageChangeListener(slideChangeListener)
        }
    }

    fun create(slides: List<ImageSliderDto>, fragmentManager: FragmentManager) {
        ScreenPagerAdapter(fragmentManager).apply {
            for (slide in slides) {
                addFragment(ImageSliderFragment.newInstance(slide.imageUrl), slide.title)
            }
        }.also {
            setupViewPager(it)
            pageIndicator.createIndicators(slides.size, visibleSlidePosition)
        }
    }

    // required to be called on onDestroyView
    fun removeViewPagerChangeListener() {
        imageslider_viewpager.removeOnPageChangeListener(slideChangeListener)
    }
}