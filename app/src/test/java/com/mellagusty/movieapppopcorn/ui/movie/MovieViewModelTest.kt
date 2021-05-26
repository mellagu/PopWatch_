package com.mellagusty.movieapppopcorn.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mellagusty.movieapppopcorn.data.DataDummyTest
import com.mellagusty.movieapppopcorn.data.remote.Repository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @Mock
    private lateinit var repository: Repository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(repository)
    }

    @Test
    fun getListMovie() {
        `when`(repository.getNowPlayingMovie()).thenReturn(DataDummyTest.generateDummyMovies())
        movieViewModel.setNowPlayingMovie()
        val movielist = movieViewModel.getNowPlayingMovie()
        verify(repository).getNowPlayingMovie()
        assertNotNull(movielist)
        assertEquals(1, movielist.value?.size)

    }

    @Test
    fun getDetailMovie() {
        val detailMovie = DataDummyTest.generateDummyDetailMovie()
        val movie_id = detailMovie.value?.id

        `when`(repository.getDetailMovie(movie_id.toString())).thenReturn(detailMovie)
        movieViewModel.setDetailMovie(movie_id.toString())
        val movie = movieViewModel.gerDetailMovie()
        assertNotNull(movie)
        assertEquals(detailMovie.value?.id, movie.value?.id)
        assertEquals(detailMovie.value?.original_title, movie.value?.original_title)
        assertEquals(detailMovie.value?.overview, movie.value?.overview)
        assertEquals(detailMovie.value?.genres, movie.value?.genres)
        assertEquals(detailMovie.value?.vote_average, movie.value?.vote_average)
        assertEquals(detailMovie.value?.tagline, movie.value?.tagline)
        assertEquals(detailMovie.value?.status, movie.value?.status)
    }

    @Test
    fun addToFavoriteMovie() {
        val dummyFavoriteMovie = DataDummyTest.generateDummyMovie()
        movieViewModel.addToFavoriteMovie(dummyFavoriteMovie)
        verify(repository, Mockito.times(1)).addToFavoriteMovie(dummyFavoriteMovie)
    }

    @Test
    fun removeFromFavoriteMovie() {
        val dummyFavoriteMovie = DataDummyTest.generateDummyMovie()
        movieViewModel.removeMovieFavorite(dummyFavoriteMovie.id)
        verify(repository, Mockito.times(1)).removeMovieFavorite(dummyFavoriteMovie.id)

    }


}