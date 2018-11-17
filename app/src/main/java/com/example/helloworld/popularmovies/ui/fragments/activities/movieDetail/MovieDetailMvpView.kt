package com.example.helloworld.popularmovies.ui.fragments.activities.movieDetail

import com.example.helloworld.popularmovies.base.MvpView
import com.example.helloworld.popularmovies.models.SearchedUsers

interface MovieDetailMvpView : MvpView {

    fun showProgress()
    fun hideProgress()
    fun displayToast(msg: String)
    fun setContentOnViews(searchedUsers: SearchedUsers)
    fun openMovieVideoActivity()

}