package com.example.helloworld.popularmovies.ui.fragments.activities.movieVideo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.helloworld.popularmovies.MoviesApp
import com.example.helloworld.popularmovies.R
import com.example.helloworld.popularmovies.base.BaseActivity
import com.example.helloworld.popularmovies.models.Result
import com.example.helloworld.popularmovies.remote.DataManager
import com.example.helloworld.popularmovies.ui.fragments.adapters.MovieVideoAdapter
import com.example.helloworld.popularmovies.ui.fragments.adapters.PopularMoviesAdapter
import com.example.helloworld.popularmovies.utils.Constants
import kotlinx.android.synthetic.main.activity_movie_video.*
import android.content.Intent
import android.net.Uri


class MovieVideoActivity : BaseActivity(), MovieVideoMvpView {

    private lateinit var dataManager: DataManager
    private lateinit var movieVideoPresenter: MovieVideoPresenter<MovieVideoMvpView>
    private lateinit var movieId: String
    private lateinit var mAdapter: MovieVideoAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_video)

        dataManager = (application as MoviesApp).getDataManager()
        progressBar = findViewById(R.id.pb_bar)

        movieId = intent.getStringExtra("movieId")

        movieVideoPresenter = MovieVideoPresenter(dataManager)
        movieVideoPresenter.attachView(this)
        movieVideoPresenter.getMovieVideos(movieId, Constants.API_KEY)

    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun displayToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun setUpAdapter(results: List<Result>) {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = layoutManager
        mAdapter = MovieVideoAdapter(this, results, object : MovieVideoAdapter.onItemClickListener {
            override fun OnItemClicked(position: Int) {
                openVideo(results[position].key!!)
            }

        })
        recyclerview.adapter = mAdapter

    }

    override fun openVideo(key: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$key")))
    }

    override fun onDestroy() {
        super.onDestroy()
        movieVideoPresenter.detachView(this)
    }

}
