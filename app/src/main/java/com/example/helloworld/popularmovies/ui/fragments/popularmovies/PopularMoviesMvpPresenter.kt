package com.example.helloworld.popularmovies.ui.fragments.popularmovies

import com.example.helloworld.popularmovies.base.BasePresenter
import com.example.helloworld.popularmovies.base.MvpPresenter
import com.example.helloworld.popularmovies.models.MovieResponse

interface PopularMoviesMvpPresenter<V: PopularMoviesMvpView> : MvpPresenter<V> {


    fun fetchPopularMovies(apiKey: String)

}