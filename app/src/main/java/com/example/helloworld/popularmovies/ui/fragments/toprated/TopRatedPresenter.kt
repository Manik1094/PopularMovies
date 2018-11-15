package com.example.helloworld.popularmovies.ui.fragments.toprated

import android.annotation.SuppressLint
import android.util.Log
import com.example.helloworld.popularmovies.base.BasePresenter
import com.example.helloworld.popularmovies.models.MovieResponse
import com.example.helloworld.popularmovies.remote.ApiManager
import com.example.helloworld.popularmovies.remote.DataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeoutException

class TopRatedPresenter<V : TopRatedMvpView>(dataManager: DataManager) : BasePresenter<V>(dataManager), TopRatedMvpPresenter<V> {

    var dataManager: DataManager
    var apiManager: ApiManager

    init {
        this.dataManager = dataManager
        apiManager = ApiManager.instance()
    }

    @SuppressLint("CheckResult")
    override fun fetchTopRatedMovies(apiKey: String) {

        dataManager.getTopRatedMovies(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieResponse>() {

                override fun onSuccess(movieResponse: MovieResponse) {
                    getMvpView().hideProgress()
                    getMvpView().initializeAdapter(movieResponse)
                }

                override fun onError(e: Throwable) {

                    getMvpView().hideProgress()
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