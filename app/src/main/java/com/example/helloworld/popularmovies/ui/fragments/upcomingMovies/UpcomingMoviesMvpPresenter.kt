package com.example.helloworld.popularmovies.ui.fragments.upcomingMovies

import com.example.helloworld.popularmovies.base.MvpPresenter

interface UpcomingMoviesMvpPresenter<V : UpcomingMoviesMvpView> : MvpPresenter<V> {

    fun fetchUpcomingMovies(apiKey : String)
}