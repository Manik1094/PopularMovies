package com.example.helloworld.popularmovies.ui.fragments.populartvshows

import android.util.Log
import com.example.helloworld.popularmovies.base.BasePresenter
import com.example.helloworld.popularmovies.models.MovieResponse
import com.example.helloworld.popularmovies.remote.DataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeoutException

class PopularTvShowsPresenter<V : PopularTvShowsMvpView>(dataManager: DataManager) : PopularTvShowsMvpPresenter<V> , BasePresenter<V>(dataManager) {



    private lateinit var mDataManager : DataManager

    init {
        mDataManager = dataManager
    }

    override fun fetchPopularTvShows(apiKey: String) {
        getMvpView().showHideProgress(true)

        mDataManager.getPopularTvShows(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                override fun onSuccess(movieResponse: MovieResponse) {
                    getMvpView().showHideProgress(false)
                    getMvpView().initializeAdapter(movieResponse)
                }

                override fun onError(e: Throwable) {
                    getMvpView().showHideProgress(false)
                    Log.e("TAG", "Eror: " + e.message)
                    when(e) {
                        is IOException -> {

                            getMvpView().showToast("Please check your internet connection")
                        }
                        is TimeoutException -> {
                            getMvpView().showToast("Request Timed out")
                        }
                        else -> {
                            getMvpView().showToast("Something went wrong")
                        }
                    }
                }

            })

    }



}