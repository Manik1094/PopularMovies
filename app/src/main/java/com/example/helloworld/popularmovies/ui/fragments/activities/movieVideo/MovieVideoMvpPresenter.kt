package com.example.helloworld.popularmovies.ui.fragments.activities.movieVideo

import com.example.helloworld.popularmovies.base.MvpPresenter

interface MovieVideoMvpPresenter<V : MovieVideoMvpView> : MvpPresenter<V> {

    fun getMovieVideos(movieId: String, apiKey: String)
}