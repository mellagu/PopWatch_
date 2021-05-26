package com.mellagusty.movieapppopcorn.di

import android.app.Application
import android.content.Context
import com.mellagusty.movieapppopcorn.data.local.LocalDataSource
import com.mellagusty.movieapppopcorn.data.local.PopWatchDatabase
import com.mellagusty.movieapppopcorn.data.remote.Repository
import com.mellagusty.movieapppopcorn.data.remote.RemoteDataSource
import com.mellagusty.movieapppopcorn.data.remote.api.RetrofitClient

object PopWatchInjection {
    fun provideRepository(context: Context): Repository {

        val api = RetrofitClient.API_INSTANCE
        val database = PopWatchDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstances(api)
        val localDataSource = LocalDataSource.getInstances(database.favoritePopWatchDao())

        return Repository.getInstance(remoteDataSource,localDataSource, context.applicationContext as Application)
    }
}