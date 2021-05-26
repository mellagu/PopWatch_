package com.mellagusty.movieapppopcorn.data.local

import androidx.paging.DataSource
import com.mellagusty.movieapppopcorn.data.remote.Poster
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSource(private val dao: PopWatchDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstances(dao: PopWatchDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dao).apply {
                INSTANCE = this
            }

    }

    fun getFavoriteMovie(): DataSource.Factory<Int, Poster> {
        return dao.getMovieFavorite()
    }

    fun getFavoriteTV(): DataSource.Factory<Int,Poster> {
        return dao.getTVFavorite()

    }

    fun addToFavoriteMovie(movie: MoviePoster) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.addMovieFavorite(movie)
        }
    }

    fun removeMovieFavorite(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.removeMovieFavorite(id)
        }
    }

    fun addToFavoriteTV(serial: TvPoster){
        CoroutineScope(Dispatchers.IO).launch {
            dao.addTVFavorite(serial)
        }
    }

    fun removeTVFavorite(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.removeTVFavorite(id)
        }
    }



}