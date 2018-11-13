package com.example.helloworld.popularmovies.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.helloworld.popularmovies.R

open class BaseActivity : AppCompatActivity(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
