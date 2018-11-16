package com.example.helloworld.popularmovies.ui.fragments.upcomingMovies

import com.example.helloworld.popularmovies.base.MvpView
import com.example.helloworld.popularmovies.models.MovieResponse

interface UpcomingMoviesMvpView : MvpView {

    fun showHideProgress(boolean: Boolean)
    fun showToast(message : String)
    fun initializeAdapter(movieResponse: MovieResponse)
}