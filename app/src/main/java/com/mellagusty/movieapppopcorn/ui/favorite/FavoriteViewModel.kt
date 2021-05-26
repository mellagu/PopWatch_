package com.mellagusty.movieapppopcorn.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mellagusty.movieapppopcorn.data.remote.Poster
import com.mellagusty.movieapppopcorn.data.remote.Repository

class FavoriteViewModel(private val mRepository: Repository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<Poster>> = mRepository.getFavoriteMovie()

    fun getFavoriteTV(): LiveData<PagedList<Poster>> = mRepository.getFavoriteTV()
}