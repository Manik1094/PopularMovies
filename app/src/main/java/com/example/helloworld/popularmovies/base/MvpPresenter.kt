package com.example.helloworld.popularmovies.base

interface MvpPresenter<V : MvpView> {

    fun attachView(mvpView: V)
    fun detachView(mvpView: V)

}