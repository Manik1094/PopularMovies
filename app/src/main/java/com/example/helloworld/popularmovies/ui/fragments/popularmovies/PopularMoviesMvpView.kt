package com.example.helloworld.popularmovies.ui.fragments.popularmovies

import com.example.helloworld.popularmovies.base.MvpView
import com.example.helloworld.popularmovies.models.MovieResponse

interface PopularMoviesMvpView : MvpView {

    fun showProgress()
    fun hideProgress()
    fun showToast(msg: String)
    fun initializeAdapter(movieResponse: MovieResponse)
    fun openMovieDetailActivity(id: String, title: String)

}