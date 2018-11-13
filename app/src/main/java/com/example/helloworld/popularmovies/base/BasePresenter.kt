package com.example.helloworld.popularmovies.base

import com.example.helloworld.popularmovies.remote.DataManager

open class BasePresenter<V : MvpView>(dataManager: DataManager) : MvpPresenter<V> {

    private var mDataManager: DataManager

    init {
        mDataManager = dataManager
    }

    private var mMvpView: V? = null

    override fun attachView(mvpView: V) {
        mMvpView = mvpView
    }

    override fun detachView(mvpView: V) {
        mMvpView = mvpView
        mMvpView = null
    }

    fun getMvpView(): V {
        return mMvpView!!
    }

}