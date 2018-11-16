package com.example.helloworld.popularmovies.ui.fragments.populartvshows


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
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
import com.example.helloworld.popularmovies.ui.fragments.adapters.PopularTvShowsAdapter
import com.example.helloworld.popularmovies.utils.Constants
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class PopularTvShowsFragment : BaseFragment() , PopularTvShowsMvpView {



    private lateinit var dataManager : DataManager
    private lateinit var popularTvShowsMvpPresenter : PopularTvShowsPresenter<PopularTvShowsMvpView>
    private lateinit var progressBar : ProgressBar
    private lateinit var mAdapter : PopularTvShowsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_popular_tv_shows, container, false)

        dataManager = (activity!!.application as MoviesApp).getDataManager()
        progressBar = view.findViewById(R.id.pb_bar)
        popularTvShowsMvpPresenter = PopularTvShowsPresenter(dataManager)
        popularTvShowsMvpPresenter.attachView(this)
        this.showHideProgress(false)
        popularTvShowsMvpPresenter.fetchPopularTvShows(Constants.API_KEY)

        return view
    }

    override fun showHideProgress(status: Boolean) {

        if (status){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.INVISIBLE
        }
    }

    override fun initializeAdapter(movieResponse: MovieResponse) {

        mAdapter = PopularTvShowsAdapter(activity!!.applicationContext, movieResponse)
        val gridLayoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = gridLayoutManager
        recyclerview.adapter = mAdapter
    }
    override fun showToast(message: String) {

    }

}
