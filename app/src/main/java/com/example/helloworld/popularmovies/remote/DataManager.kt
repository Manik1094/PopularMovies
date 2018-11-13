package com.example.helloworld.popularmovies.remote

import com.example.helloworld.popularmovies.models.MovieResponse
import io.reactivex.Single
import retrofit2.http.Query

class DataManager {

    private val apiManager = ApiManager.instance()

    fun getPopularMovies(apiKey: String): Single<MovieResponse> {
        return apiManager.apiService.getPopularMovies(apiKey)
    }

}