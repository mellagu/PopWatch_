package com.mellagusty.movieapppopcorn.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mellagusty.movieapppopcorn.data.remote.Repository
import com.mellagusty.movieapppopcorn.di.PopWatchInjection
import com.mellagusty.movieapppopcorn.ui.favorite.FavoriteViewModel
import com.mellagusty.movieapppopcorn.ui.movie.MovieViewModel
import com.mellagusty.movieapppopcorn.ui.tvshow.TvShowViewModel

class PopViewModelRequest private constructor(private val mrepository: Repository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: PopViewModelRequest? = null

        fun getInstance(context: Context): PopViewModelRequest =
            instance ?: synchronized(this) {
                PopViewModelRequest(PopWatchInjection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(classModel: Class<T>): T {
            return when {
                classModel.isAssignableFrom(MovieViewModel::class.java) -> {
                    MovieViewModel(mrepository) as T
                }
                classModel.isAssignableFrom(TvShowViewModel::class.java) -> {
                    TvShowViewModel(mrepository) as T
                }
                classModel.isAssignableFrom(FavoriteViewModel::class.java) -> {
                    FavoriteViewModel(mrepository) as T
                }
                else -> throw Throwable("Unknown ViewModel class: " + classModel.name)
            }
        }

}