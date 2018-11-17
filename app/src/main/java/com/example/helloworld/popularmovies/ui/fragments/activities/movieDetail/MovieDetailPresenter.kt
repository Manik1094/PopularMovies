package com.example.helloworld.popularmovies.ui.fragments.activities.movieDetail

import android.annotation.SuppressLint
import android.util.Log
import com.example.helloworld.popularmovies.base.BasePresenter
import com.example.helloworld.popularmovies.models.SearchedUsers
import com.example.helloworld.popularmovies.remote.ApiManager
import com.example.helloworld.popularmovies.remote.DataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeoutException

class MovieDetailPresenter<V : MovieDetailMvpView>(dataManager: DataManager) : BasePresenter<V>(dataManager) , MovieDetailMvpPresenter<V> {

    private var dataManager: DataManager

    init {
        this.dataManager = dataManager
    }


    @SuppressLint("CheckResult")
    override fun getMovieDetail(id: String, apiKey: String) {

        getMvpView().showProgress()

        dataManager.getMovieDetail(id, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<SearchedUsers>() {

                override fun onSuccess(searchedUsers: SearchedUsers) {
                    getMvpView().hideProgress()
                    getMvpView().setContentOnViews(searchedUsers)
                }


                override fun onError(e: Throwable) {
                    getMvpView().hideProgress()
                    Log.e("MovieDetailPresenter", "Error: " + e.message)
                    when(e) {
                        is IOException -> {

                            getMvpView().displayToast("Please check your internet connection")
                        }
                        is TimeoutException -> {
                            getMvpView().displayToast("Request Timed out")
                        }
                        else -> {
                            getMvpView().displayToast("Something went wrong")
                        }
                    }
                }

            })
    }



}