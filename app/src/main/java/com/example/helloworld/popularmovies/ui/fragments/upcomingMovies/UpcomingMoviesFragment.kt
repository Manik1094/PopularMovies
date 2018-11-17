package com.example.helloworld.popularmovies.ui.fragments.upcomingMovies


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.helloworld.popularmovies.MoviesApp

import com.example.helloworld.popularmovies.R
import com.example.helloworld.popularmovies.base.BaseFragment
import com.example.helloworld.popularmovies.models.MovieResponse
import com.example.helloworld.popularmovies.remote.DataManager
import com.example.helloworld.popularmovies.ui.fragments.adapters.PopularMoviesAdapter
import com.example.helloworld.popularmovies.utils.Constants


class UpcomingMoviesFragment : BaseFragment() , UpcomingMoviesMvpView {

    private lateinit var dataManager : DataManager
    private lateinit var upcomingMoviesPresenter : UpcomingMoviesMvpPresenter<UpcomingMoviesMvpView>
    private lateinit var mAdapter : PopularMoviesAdapter
    private lateinit var progressBar : ProgressBar
    private lateinit var recyclerview : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_upcoming_movies, container, false)
        dataManager = (activity!!.application as MoviesApp).getDataManager()
        progressBar = view.findViewById(R.id.pb_bar)
        recyclerview = view.findViewById(R.id.recyclerview)
        upcomingMoviesPresenter = UpcomingMoviesPresenter(dataManager)
        upcomingMoviesPresenter.attachView(this)
        this.showHideProgress(false)
        upcomingMoviesPresenter.fetchUpcomingMovies(Constants.API_KEY)


        return view
    }

    override fun showHideProgress(boolean: Boolean) {
        if (boolean){
            progressBar.visibility = View.VISIBLE
        }
        else{
            progressBar.visibility = View.INVISIBLE
        }

    }

    override fun showToast(message: String) {

    }

    override fun initializeAdapter(movieResponse: MovieResponse) {

        mAdapter = PopularMoviesAdapter(activity!!.applicationContext, movieResponse, object : PopularMoviesAdapter.onItemClickListener {
            override fun OnItemClicked(position: Int) {


            }

        })
        val gridLayoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = gridLayoutManager
        recyclerview.adapter = mAdapter
    }


}
