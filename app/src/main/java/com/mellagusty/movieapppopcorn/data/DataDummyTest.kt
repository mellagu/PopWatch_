package com.mellagusty.movieapppopcorn.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mellagusty.movieapppopcorn.data.local.MoviePoster
import com.mellagusty.movieapppopcorn.data.local.PopWatchDao
import com.mellagusty.movieapppopcorn.data.local.TvPoster
import com.mellagusty.movieapppopcorn.data.remote.*

object DataDummyTest {

    fun generateDummyMovies(): LiveData<List<Poster>> {

        val listMovies = MutableLiveData<List<Poster>>()

        listMovies.value = arrayListOf(
            Poster(
                "8932874",
                "2021-02-24",
                "2020-10-16",
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "Chaos Walking",
                "The Marksman"

            )
        )

        return listMovies
    }

    fun generateDummyDetailMovie(): LiveData<MoviePosterDetail> {

        val detailMovie = MutableLiveData<MoviePosterDetail>()

        detailMovie.postValue(
            MoviePosterDetail(
                873974,
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "Chaos Walking",
                "In Prentisstown, Todd has been brought up to believe that the Spackle released a germ that killed all the women ",
                "en",
                listOf(GenreModel("drama", 32)),
                8.3,
                null,
                "released"
            )
        )
        return detailMovie
    }

    fun generateDummyTvShow(): LiveData<List<Poster>> {

        val listTvShow = MutableLiveData<List<Poster>>()

        listTvShow.value = arrayListOf(
            Poster(
                "8932874",
                "2021-02-24",
                "2020-10-16",
                "jfdhfjd.jpg",
                "Chaos Walking",
                "The Marksman"
            )

        )
        return listTvShow
    }

    fun generateDummyDetailTvShow(): LiveData<TvShowPosterDetail> {

        val detailTvShow = MutableLiveData<TvShowPosterDetail>()

        detailTvShow.postValue(
            TvShowPosterDetail(
                894593,
                "Wandavision",
                "en",
                "Several hundred years ago, humans were nearly exterminated by Titans. Titans are typically several stories tall.",
                "Released",
                9,
                9.3,
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                listOf(GenreModels("thriller", 36)),
                "Scripted",
                null
            )
        )
        return detailTvShow
    }

    fun generateDummyFavoriteMovies(): List<Poster> {
        var listMovies = ArrayList<Poster>()

        listMovies = arrayListOf(
            Poster(
                "8932874",
                "2021-02-24",
                "2020-10-16",
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "Chaos Walking",
                "The Marksman"

            )
        )

        return listMovies
    }

    fun generateDummyFavoriteTV(): List<Poster> {
        var listSerial = ArrayList<Poster>()

        listSerial = arrayListOf(
            Poster(
                "8932874",
                "2021-02-24",
                "2020-10-16",
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "Chaos Walking",
                "The Marksman"

            )
        )

        return listSerial
    }

    fun generateDummyMovie(): MoviePoster {
        return MoviePoster(
            "8932874",
            "2021-02-24",
            "2020-10-16",
            "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg"
        )
    }


    fun generateDummyTV(): TvPoster {
        return TvPoster(
            "8932874",
            "2021-02-24",
            "2020-10-16",
            "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg"
        )
    }


}