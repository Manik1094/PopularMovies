package com.example.helloworld.popularmovies.ui.fragments.toprated

import com.example.helloworld.popularmovies.base.MvpPresenter

interface TopRatedMvpPresenter<V : TopRatedMvpView> : MvpPresenter<V> {

    fun fetchTopRatedMovies(apiKey: String)

}