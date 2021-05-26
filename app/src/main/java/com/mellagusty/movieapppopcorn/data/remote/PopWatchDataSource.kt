package com.mellagusty.movieapppopcorn.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mellagusty.movieapppopcorn.data.local.MoviePoster
import com.mellagusty.movieapppopcorn.data.local.TvPoster

interface PopWatchDataSource {

    fun getNowPlayingMovie(): LiveData<List<Poster>>

    fun getDetailMovie(movie_id: String): LiveData<MoviePosterDetail>

    fun getPopularTvShow(): LiveData<List<Poster>>

    fun getDetailTvShow(tv_id: String): LiveData<TvShowPosterDetail>

    //PagedList for Favorite
    fun getFavoriteMovie(): LiveData<PagedList<Poster>>

    fun getFavoriteTV(): LiveData<PagedList<Poster>>

    //Add Delete for Favorite
    fun addToFavoriteMovie(movie: MoviePoster)

    fun removeMovieFavorite(id: String)

    fun addToFavoriteTV(serial: TvPoster)

    fun removeTVFavorite(id: String)


}