package com.mellagusty.movieapppopcorn.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mellagusty.movieapppopcorn.data.local.MoviePoster
import com.mellagusty.movieapppopcorn.data.remote.MoviePosterDetail
import com.mellagusty.movieapppopcorn.data.remote.Poster
import com.mellagusty.movieapppopcorn.data.remote.Repository

class MovieViewModel(private val repository: Repository) : ViewModel() {
    lateinit var listMovie: LiveData<List<Poster>>
    lateinit var detailMovie: LiveData<MoviePosterDetail>

    //setforNowPlayingMovie
    fun setNowPlayingMovie() {
        listMovie = repository.getNowPlayingMovie()
    }

    fun getNowPlayingMovie() = listMovie

    //SetGet Detail Movie
    fun setDetailMovie(movie_id: String) {
        detailMovie = repository.getDetailMovie(movie_id)
    }

    fun gerDetailMovie() = detailMovie

    //Room
    fun addToFavoriteMovie(movie: MoviePoster) = repository.addToFavoriteMovie(movie)

    suspend fun checkFavoriteMovie(id: String) = repository.checkFavoriteMovie(id)

    fun removeMovieFavorite(id: String) = repository.removeMovieFavorite(id)


}
