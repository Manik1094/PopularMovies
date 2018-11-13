package com.example.helloworld.popularmovies.models

import com.google.gson.annotations.SerializedName



class MovieResponse {

    @SerializedName("results")
    public var movies: List<Movie> = ArrayList()

    @SerializedName("total_results")
    private var totalResults: Int = 0

    @SerializedName("total_pages")
    private var totalPages: Int = 0


}