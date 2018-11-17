package com.example.helloworld.popularmovies.ui.fragments.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.helloworld.popularmovies.R
import com.example.helloworld.popularmovies.models.MovieResponse
import kotlinx.android.synthetic.main.popular_movies_list.view.*

class PopularMoviesAdapter(context: Context, movieResponse: MovieResponse, listener: onItemClickListener): RecyclerView.Adapter<PopularMoviesAdapter.MyViewHolder>() {

    private var context: Context
    private var movieResponse: MovieResponse
    private var mListener: onItemClickListener

    init {
        this.context = context
        this.movieResponse = movieResponse
        mListener = listener

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.popular_movies_list, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieResponse.movies.size
    }

    interface onItemClickListener {
        fun OnItemClicked(position: Int)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tv_movie_title.text = movieResponse.movies[position].title
        holder.itemView.tv_movie_release_date.text = movieResponse.movies[position].releaseDate

        var BASE_URL = "https://image.tmdb.org/t/p/w500"
        var img  = movieResponse.movies[position].posterPath
        var IMG_URL = BASE_URL + img

        Glide.with(context).load(IMG_URL)
            .into(holder.itemView.iv_movie_icon)

        holder.itemView.setOnClickListener {
            mListener.OnItemClicked(position)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}