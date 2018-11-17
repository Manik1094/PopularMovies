package com.example.helloworld.popularmovies.ui.fragments.popularmovies


import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.helloworld.popularmovies.MoviesApp

import com.example.helloworld.popularmovies.R
import com.example.helloworld.popularmovies.base.BaseFragment
import com.example.helloworld.popularmovies.models.MovieResponse
import com.example.helloworld.popularmovies.remote.DataManager
import com.example.helloworld.popularmovies.ui.fragments.activities.movieDetail.MovieDetailActivity
import com.example.helloworld.popularmovies.ui.fragments.adapters.PopularMoviesAdapter
import com.example.helloworld.popularmovies.utils.Constants
import kotlinx.android.synthetic.main.fragment_popular_movies.*


/**
 * A simple [Fragment] subclass.
 *
 */
class PopularMoviesFragment : BaseFragment(), PopularMoviesMvpView {

    private lateinit var dataManager: DataManager
    private lateinit var popularMoviesPresenter : PopularMoviesPresenter<PopularMoviesMvpView>
    private lateinit var mAdapter: PopularMoviesAdapter
    private lateinit var progressBar : ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View =  inflater.inflate(R.layout.fragment_popular_movies, container, false)
        dataManager = (activity!!.application as MoviesApp).getDataManager()
        progressBar = view.findViewById(R.id.pb_bar)
        popularMoviesPresenter = PopularMoviesPresenter(dataManager)
        popularMoviesPresenter.attachView(this)
        this.hideProgress()
        popularMoviesPresenter.fetchPopularMovies(Constants.API_KEY)

        // Inflate the layout for this fragment

        return view
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showToast(msg: String) {
      //  Toast.makeText(this, msg, Toast.LENGTH_SHORT).show
      //  Toast.makeText(this , msg , Toast.LENGTH_SHORT).
    }

    override fun openMovieDetailActivity(id: String, title: String) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("title", title)
        startActivity(intent)
    }


    override fun initializeAdapter(movieResponse: MovieResponse) {

        mAdapter = PopularMoviesAdapter(activity!!.applicationContext, movieResponse, object : PopularMoviesAdapter.onItemClickListener{
            override fun OnItemClicked(position: Int) {
                openMovieDetailActivity(movieResponse.movies[position].Id!!, movieResponse.movies[position].title!!)
            }

        })
        val gridLayoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = gridLayoutManager
        recyclerview.adapter = mAdapter

    }

}
