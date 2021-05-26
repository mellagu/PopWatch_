package com.mellagusty.movieapppopcorn.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mellagusty.movieapppopcorn.data.local.TvPoster
import com.mellagusty.movieapppopcorn.data.remote.Poster
import com.mellagusty.movieapppopcorn.data.remote.Repository
import com.mellagusty.movieapppopcorn.data.remote.TvShowPosterDetail

class TvShowViewModel(private val repository: Repository) : ViewModel() {
    lateinit var listTV: LiveData<List<Poster>>
    lateinit var detailTV: LiveData<TvShowPosterDetail>

    //setforPopularTVShow
    fun setPopularTvShow() {
        listTV = repository.getPopularTvShow()
    }

    fun getPopularTvShow() = listTV

    //setforDetailTVShow
    fun setDetailTvShow(tv_id: String) {
        detailTV = repository.getDetailTvShow(tv_id)
    }

    fun getDetailTVShow() = detailTV

    //room
    fun addToFavoriteTV(serial: TvPoster) = repository.addToFavoriteTV(serial)
    suspend fun checkFavoriteTV(id: String) = repository.checkFavoriteTV(id)
    fun removeTVFavorite(id: String) = repository.removeTVFavorite(id)


}