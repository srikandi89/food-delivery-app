package com.srikandi.uikit.imageslider

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.srikandi.uikit.R
import kotlinx.android.synthetic.main.image_slider_fragment.*

class ImageSliderFragment : Fragment(R.layout.image_slider_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // todo : add placeHolder(...) for loading indicator
        val url = arguments?.getString(IMAGE_URL)
        url?.let {
            Glide.with(this).load(it).centerCrop().into(imageslider_imageview)
        }
    }

    companion object {
        private const val IMAGE_URL = "image_url"

        fun newInstance(url: String) = ImageSliderFragment().apply {
            arguments = Bundle().apply {
                putString(IMAGE_URL, url)
            }
        }
    }
}