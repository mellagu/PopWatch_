package com.mellagusty.movieapppopcorn.data.remote

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mellagusty.movieapppopcorn.data.local.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    application: Application,
) : PopWatchDataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            application: Application
        ): Repository =
            instance ?: synchronized(this) {
                Repository(remoteData, localDataSource, application).apply { instance = this }
            }
    }


    //room for favorite
    private var popWatchDao: PopWatchDao?
    private var popWatchDB: PopWatchDatabase?

    init {
        popWatchDB = PopWatchDatabase.getDatabase(application)
        popWatchDao = popWatchDB?.favoritePopWatchDao()
    }


    //to the api

    override fun getNowPlayingMovie() = remoteDataSource.getNowPlayingMovie()

    override fun getDetailMovie(movie_id: String) = remoteDataSource.getDetailMovie(movie_id)

    override fun getPopularTvShow() = remoteDataSource.getPopularTvShow()

    override fun getDetailTvShow(tv_id: String) = remoteDataSource.getDetailTvShow(tv_id)


    //to Room local

    //Movie Favorite
    override fun getFavoriteMovie(): LiveData<PagedList<Poster>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }


    override fun addToFavoriteMovie(movie: MoviePoster) {
        CoroutineScope(Dispatchers.IO).launch {
            popWatchDao?.addMovieFavorite(movie)
        }
    }

    suspend fun checkFavoriteMovie(id: String) = popWatchDao?.checkMovieFavorite(id)

    override fun removeMovieFavorite(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            popWatchDao?.removeMovieFavorite(id)
        }
    }


    //Television Favorite
    override fun getFavoriteTV(): LiveData<PagedList<Poster>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTV(), config).build()
    }

    override fun addToFavoriteTV(serial: TvPoster) {
        CoroutineScope(Dispatchers.IO).launch {
            popWatchDao?.addTVFavorite(serial)
        }
    }

    suspend fun checkFavoriteTV(id: String) = popWatchDao?.checkTVFavorite(id)

    override fun removeTVFavorite(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            popWatchDao?.removeTVFavorite(id)
        }
    }


}