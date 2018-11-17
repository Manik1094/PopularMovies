package com.example.helloworld.popularmovies.ui.fragments.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.helloworld.popularmovies.R
import com.example.helloworld.popularmovies.models.MovieResponse
import com.example.helloworld.popularmovies.models.Result
import kotlinx.android.synthetic.main.movie_video_list.view.*

class MovieVideoAdapter(context: Context, results: List<Result>, listener: MovieVideoAdapter.onItemClickListener): RecyclerView.Adapter<MovieVideoAdapter.MyViewHolder>() {

    private var context: Context
    private var results: List<Result>
    private var mListener: MovieVideoAdapter.onItemClickListener

    init {
        this.context = context
        this.results = results
        mListener = listener

    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.movie_video_list, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {

        if (results.isEmpty()) {
            return 0
        }
        return results.size
    }

    interface onItemClickListener {
        fun OnItemClicked(position: Int)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tv_video_name.text = results[position].name
        holder.itemView.tv_video_type.text = results[position].type
        holder.itemView.setOnClickListener {
            mListener.OnItemClicked(position)
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}