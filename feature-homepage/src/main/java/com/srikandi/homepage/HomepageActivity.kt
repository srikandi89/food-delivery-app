package com.srikandi.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.srikandi.common.extensions.addFragment
import com.srikandi.homepage.screen.HomepageShowcaseFragment

class HomepageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        if (savedInstanceState == null) {
            addFragment<HomepageShowcaseFragment>(R.id.homepage_activity_framelayout)
        }
    }
}