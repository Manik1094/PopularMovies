package com.example.helloworld.popularmovies.ui.fragments.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.helloworld.popularmovies.ui.fragments.popularmovies.PopularMoviesFragment
import com.example.helloworld.popularmovies.ui.fragments.toprated.TopRatedMoviesFragment

class CustomFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            PopularMoviesFragment()
        } else
            TopRatedMoviesFragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {

        if (position == 0) {
            return "Popular"
        } else if (position == 1) {
            return "Top Rated"
        }
        return super.getPageTitle(position)
    }
}