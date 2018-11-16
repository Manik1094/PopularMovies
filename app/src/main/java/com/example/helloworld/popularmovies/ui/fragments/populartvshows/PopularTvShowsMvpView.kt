package com.example.helloworld.popularmovies.ui.fragments.populartvshows

import com.example.helloworld.popularmovies.base.MvpView
import com.example.helloworld.popularmovies.models.MovieResponse

interface PopularTvShowsMvpView : MvpView {

    fun showHideProgress(status: Boolean)
    fun initializeAdapter(movieResponse: MovieResponse)
    fun showToast(message : String)
}