package com.example.helloworld.popularmovies.ui.fragments.toprated


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
import com.example.helloworld.popularmovies.ui.fragments.adapters.TopRatedMoviesAdapter
import com.example.helloworld.popularmovies.utils.Constants
import kotlinx.android.synthetic.main.fragment_popular_movies.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TopRatedMoviesFragment : BaseFragment(), TopRatedMvpView {

    private lateinit var dataManager: DataManager
    private lateinit var topRatedPresenter: TopRatedPresenter<TopRatedMvpView>
    private lateinit var progressBar : ProgressBar
    private lateinit var mAdapter: TopRatedMoviesAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_top_rated_movies, container, false)
        dataManager = (activity!!.application as MoviesApp).getDataManager()
        progressBar = view!!.findViewById(R.id.pb_bar)
        topRatedPresenter = TopRatedPresenter(dataManager)
        topRatedPresenter.attachView(this)
        this.hideProgress()
        topRatedPresenter.fetchTopRatedMovies(Constants.API_KEY)
        return view
    }
    
    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showToast(msg: String) {

    }

    override fun initializeAdapter(movieResponse: MovieResponse) {
        mAdapter = TopRatedMoviesAdapter(activity!!.applicationContext, movieResponse)
        val gridLayoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = gridLayoutManager
        recyclerview.adapter = mAdapter
    }


}
