package com.example.helloworld.popularmovies.ui.fragments.activities.movieDetail

import com.example.helloworld.popularmovies.base.MvpPresenter

interface MovieDetailMvpPresenter<V : MovieDetailMvpView> : MvpPresenter<V> {

    fun getMovieDetail(id: String, apiKey: String)

}