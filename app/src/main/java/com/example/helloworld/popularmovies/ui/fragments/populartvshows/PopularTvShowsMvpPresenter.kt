package com.example.helloworld.popularmovies.ui.fragments.populartvshows

import com.example.helloworld.popularmovies.base.MvpPresenter

interface PopularTvShowsMvpPresenter<V : PopularTvShowsMvpView> : MvpPresenter<V> {

    fun fetchPopularTvShows(apiKey : String)
}