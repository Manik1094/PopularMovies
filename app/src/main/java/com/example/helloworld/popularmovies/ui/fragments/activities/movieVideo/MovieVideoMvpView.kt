package com.example.helloworld.popularmovies.ui.fragments.activities.movieVideo

import com.example.helloworld.popularmovies.base.MvpView
import com.example.helloworld.popularmovies.models.Result

interface MovieVideoMvpView : MvpView {

    fun showProgress()
    fun hideProgress()
    fun displayToast(msg: String)
    fun setUpAdapter(results: List<Result>)
    fun openVideo(key: String)
}