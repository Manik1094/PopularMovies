package com.example.helloworld.popularmovies.remote

import com.example.helloworld.popularmovies.models.MovieResponse
import com.example.helloworld.popularmovies.models.SearchedUsers
import io.reactivex.Single
import retrofit2.http.Query

class DataManager {

    private val apiManager = ApiManager.instance()

    fun getPopularMovies(apiKey: String): Single<MovieResponse> {
        return apiManager.apiService.getPopularMovies(apiKey)
    }

    fun getTopRatedMovies(apiKey: String): Single<MovieResponse> {
        return apiManager.apiService.getTopRated(apiKey)
    }

    fun getPopularTvShows(apiKey: String) : Single<MovieResponse>{
        return apiManager.apiService.getPopularTvShows(apiKey)
    }
    fun getUpcomingMovies(apiKey: String) : Single<MovieResponse>{
        return apiManager.apiService.getUpcomingMovies(apiKey)
    }

    fun getMovieDetail(id: String, apiKey: String): Single<SearchedUsers> {
        return apiManager.apiService.getMovieDetail(id, apiKey)
    }

}