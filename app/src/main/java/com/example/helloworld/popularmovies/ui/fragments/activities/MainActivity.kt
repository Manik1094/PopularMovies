package com.example.helloworld.popularmovies.ui.fragments.activities

import android.os.Bundle
import com.example.helloworld.popularmovies.R
import com.example.helloworld.popularmovies.base.BaseActivity
import com.example.helloworld.popularmovies.ui.fragments.adapters.CustomFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var customFragmentPagerAdapter: CustomFragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customFragmentPagerAdapter = CustomFragmentPagerAdapter(supportFragmentManager)
        viewPager.adapter = customFragmentPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }
}
