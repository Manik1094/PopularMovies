package com.example.helloworld.popularmovies.models

import com.google.gson.annotations.SerializedName



class Movie {

    @SerializedName("poster_path")
     var posterPath: String? = null

    @SerializedName("original_name")
     var name: String? = null

    @SerializedName("adult")
     var adult: Boolean = false

    @SerializedName("overview")
     var overview: String? = null

    @SerializedName("release_date")
     var releaseDate: String? = null

    @SerializedName("genre_ids")
     var genreId: List<Int> = ArrayList()

    @SerializedName("id")
     var Id: String? = null

    @SerializedName("original_title")
     var title: String? = null

    @SerializedName("original_language")
     var orginalLang: String? = null

    @SerializedName("backdrop_path")
     var backdropPath: String? = null

    @SerializedName("popularity")
     var popularity: Double? = null

    @SerializedName("vote_count")
     var voteCount: Int? = null

    @SerializedName("video")
     var video: Boolean? = null

    @SerializedName("vote_average")
     var voteAverage: Double? = null



}