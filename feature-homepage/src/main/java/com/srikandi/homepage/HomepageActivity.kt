package com.srikandi.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.srikandi.common.extensions.addFragment
import com.srikandi.homepage.screen.showcase.HomepageShowcaseFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class HomepageActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage_activity)

        if (savedInstanceState == null) {
            addFragment<HomepageShowcaseFragment>(R.id.homepage_activity_framelayout)
        }
    }

    override fun androidInjector() = supportFragmentInjector
}