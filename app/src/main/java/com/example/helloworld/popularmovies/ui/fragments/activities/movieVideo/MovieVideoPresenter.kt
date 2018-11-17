package com.example.helloworld.popularmovies.ui.fragments.activities.movieVideo

import android.annotation.SuppressLint
import android.util.Log
import com.example.helloworld.popularmovies.base.BasePresenter
import com.example.helloworld.popularmovies.models.MovieVideo
import com.example.helloworld.popularmovies.remote.DataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeoutException

class MovieVideoPresenter<V : MovieVideoMvpView>(dataManager: DataManager) : BasePresenter<V>(dataManager), MovieVideoMvpPresenter<V> {

    private var dataManager: DataManager

    init {
        this.dataManager = dataManager
    }

    @SuppressLint("CheckResult")
    override fun getMovieVideos(movieId: String, apiKey: String) {

        getMvpView().showProgress()

        dataManager.getMovieVideos(movieId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieVideo>() {

                override fun onSuccess(movieVideo: MovieVideo) {
                    getMvpView().hideProgress()
                    getMvpView().setUpAdapter(movieVideo.results!!)
                }


                override fun onError(e: Throwable) {
                    getMvpView().hideProgress()
                    Log.e("MovieVideoPresenter", "Error: " + e.message)
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