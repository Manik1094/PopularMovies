package com.example.helloworld.popularmovies.ui.fragments.activities.movieDetail

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.helloworld.popularmovies.MoviesApp
import com.example.helloworld.popularmovies.R
import com.example.helloworld.popularmovies.base.BaseActivity
import com.example.helloworld.popularmovies.models.SearchedUsers
import com.example.helloworld.popularmovies.remote.DataManager
import com.example.helloworld.popularmovies.ui.fragments.activities.movieVideo.MovieVideoActivity
import com.example.helloworld.popularmovies.utils.Constants
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie_detail.*

class MovieDetailActivity : BaseActivity(), MovieDetailMvpView {


    lateinit var progressBar: ProgressBar
    private lateinit var movieDetailPresenter: MovieDetailPresenter<MovieDetailMvpView>
    private lateinit var dataManager: DataManager
    private lateinit var id: String
    private lateinit var movieName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        id = intent.getStringExtra("id")
        movieName = intent.getStringExtra("title")

        this.title = movieName

        dataManager = (application as MoviesApp).getDataManager()
        movieDetailPresenter = MovieDetailPresenter(dataManager)
        movieDetailPresenter.attachView(this)

        progressBar = findViewById(R.id.pb_bar)
        hideProgress()

        movieDetailPresenter.getMovieDetail(id, Constants.API_KEY)

        btn_watch_trailer.setOnClickListener {
            openMovieVideoActivity()
        }

        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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

    override fun setContentOnViews(searchedUsers: SearchedUsers) {

        val BASE_URL = "https://image.tmdb.org/t/p/w500"
        val img  = searchedUsers.posterPath
        val IMG_URL = BASE_URL + img

        Glide.with(this)
            .load(IMG_URL)
            .into(iv_poster)

        tv_release_date.text = searchedUsers.releaseDate
        tv_description.text = searchedUsers.overview
        tv_votes.text = searchedUsers.voteCount.toString()
        tv_popularity.text = searchedUsers.popularity.toString()
        tv_language.text = searchedUsers.originalLanguage

    }

    override fun openMovieVideoActivity() {
        val intent = Intent(this, MovieVideoActivity::class.java)
        intent.putExtra("movieId", id)
        startActivity(intent)
    }

}
