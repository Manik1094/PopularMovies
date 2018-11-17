package com.example.helloworld.popularmovies.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class MovieVideo {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null
}