package com.example.helloworld.popularmovies.remote

import com.example.helloworld.popularmovies.models.MovieResponse
import com.example.helloworld.popularmovies.models.SearchedUsers
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getTopRated(@Query("api_key") apiKey: String): Single<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<MovieResponse>

    @GET("tv/popular")
    fun getPopularTvShows(@Query("api_key") apiKey: String): Single<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey: String):Single<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: String, @Query("api_key") apiKey: String): Single<SearchedUsers>

}