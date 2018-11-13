package com.example.helloworld.popularmovies

import android.app.Application
import com.example.helloworld.popularmovies.remote.DataManager

class MoviesApp : Application() {

    private lateinit var dataManager: DataManager

    override fun onCreate() {
        super.onCreate()

        dataManager = DataManager()

    }

    fun getDataManager(): DataManager {
        return dataManager
    }
}