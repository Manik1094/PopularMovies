package com.example.helloworld.popularmovies.ui.fragments.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.helloworld.popularmovies.ui.fragments.popularmovies.PopularMoviesFragment
import com.example.helloworld.popularmovies.ui.fragments.populartvshows.PopularTvShowsFragment
import com.example.helloworld.popularmovies.ui.fragments.toprated.TopRatedMoviesFragment
import com.example.helloworld.popularmovies.ui.fragments.upcomingMovies.UpcomingMoviesFragment

class CustomFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            PopularMoviesFragment()
        } else if (position == 1)
            TopRatedMoviesFragment()
        else if(position == 2)
            PopularTvShowsFragment()
        else{
            UpcomingMoviesFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {

        if (position == 0) {
            return "Popular"
        } else if (position == 1) {
            return "Top Rated"
        }else if (position == 2){
            return "Tv Shows"
        }else{
            return "Upcoming Movies"
        }
        return super.getPageTitle(position)
    }
}