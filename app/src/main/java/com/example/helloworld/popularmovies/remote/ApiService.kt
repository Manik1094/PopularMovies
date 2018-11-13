package com.example.helloworld.popularmovies.remote

import com.example.helloworld.popularmovies.models.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getTopRated(@Query("api_key") apiKey: String): Single<List<MovieResponse>>

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<MovieResponse>

}