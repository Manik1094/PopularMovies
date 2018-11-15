package com.example.helloworld.popularmovies.ui.fragments.toprated

import com.example.helloworld.popularmovies.base.MvpView
import com.example.helloworld.popularmovies.models.MovieResponse

interface TopRatedMvpView : MvpView {

    fun showProgress()
    fun hideProgress()
    fun showToast(msg: String)
    fun initializeAdapter(movieResponse: MovieResponse)
}