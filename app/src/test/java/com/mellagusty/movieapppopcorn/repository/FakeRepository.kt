package com.mellagusty.movieapppopcorn.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mellagusty.movieapppopcorn.data.local.LocalDataSource
import com.mellagusty.movieapppopcorn.data.local.MoviePoster
import com.mellagusty.movieapppopcorn.data.local.TvPoster
import com.mellagusty.movieapppopcorn.data.remote.PopWatchDataSource
import com.mellagusty.movieapppopcorn.data.remote.Poster
import com.mellagusty.movieapppopcorn.data.remote.RemoteDataSource

class FakeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) :
    PopWatchDataSource {

    //from API
    override fun getNowPlayingMovie() = remoteDataSource.getNowPlayingMovie()

    override fun getDetailMovie(movie_id: String) = remoteDataSource.getDetailMovie(movie_id)

    override fun getPopularTvShow() = remoteDataSource.getPopularTvShow()

    override fun getDetailTvShow(tv_id: String) = remoteDataSource.getDetailTvShow(tv_id)

    //favorite
    override fun getFavoriteMovie(): LiveData<PagedList<Poster>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTV(): LiveData<PagedList<Poster>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTV(), config).build()
    }

    override fun addToFavoriteMovie(movie: MoviePoster) {
        localDataSource.addToFavoriteMovie(movie)
    }

    override fun removeMovieFavorite(id: String) {
        localDataSource.removeMovieFavorite(id)
    }

    override fun addToFavoriteTV(serial: TvPoster) {
        localDataSource.addToFavoriteTV(serial)
    }

    override fun removeTVFavorite(id: String) {
        localDataSource.removeTVFavorite(id)
    }


}