package com.example.helloworld.popularmovies.ui.fragments.popularmovies

import android.annotation.SuppressLint
import android.util.Log
import com.example.helloworld.popularmovies.base.BasePresenter
import com.example.helloworld.popularmovies.models.MovieResponse
import com.example.helloworld.popularmovies.remote.ApiManager
import com.example.helloworld.popularmovies.remote.DataManager
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

class PopularMoviesPresenter<V: PopularMoviesMvpView>(dataManager: DataManager) : PopularMoviesMvpPresenter<V>, BasePresenter<V>(dataManager) {


    private var mDataManager: DataManager
    private var apiManager: ApiManager

    init {
        mDataManager = dataManager
        apiManager = ApiManager.instance()
    }


    @SuppressLint("CheckResult")
    override fun fetchPopularMovies(apiKey: String) {

        getMvpView().showProgress()

        mDataManager.getPopularMovies(apiKey)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeWith(object : DisposableSingleObserver<MovieResponse>() {
               /**
                * Notifies the SingleObserver with a single item and that the [Single] has finished sending
                * push-based notifications.
                *
                *
                * The [Single] will not call this method if it calls [.onError].
                *
                * @param t
                * the item emitted by the Single
                */
               override fun onSuccess(movieResponse: MovieResponse) {

                   getMvpView().hideProgress()
                   getMvpView().initializeAdapter(movieResponse)

               }


               /**
                * Notifies the Observer that the [Observable] has experienced an error condition.
                *
                *
                * If the [Observable] calls this method, it will not thereafter call [.onNext] or
                * [.onComplete].
                *
                * @param e
                * the exception encountered by the Observable
                */
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