package com.mellagusty.movieapppopcorn.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.mellagusty.movieapppopcorn.data.DataDummyTest
import com.mellagusty.movieapppopcorn.data.local.LocalDataSource
import com.mellagusty.movieapppopcorn.data.remote.Poster
import com.mellagusty.movieapppopcorn.data.remote.RemoteDataSource
import com.mellagusty.movieapppopcorn.resource.Resource
import com.mellagusty.movieapppopcorn.util.PagedListUtil
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*


class RepositoryTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val repository = FakeRepository(remote, local)
    private val posterResponses = DataDummyTest.generateDummyFavoriteMovies()
    private val detailMovie = DataDummyTest.generateDummyDetailMovie()
    private val detailTvShow = DataDummyTest.generateDummyDetailTvShow()

    @Test
    fun getListMovie() {
        `when`(remote.getNowPlayingMovie()).thenReturn(DataDummyTest.generateDummyMovies())
        val movielist = repository.getNowPlayingMovie()
        Mockito.verify(remote).getNowPlayingMovie()
        Assert.assertNotNull(movielist)
        Assert.assertEquals(1, movielist.value?.size)

    }

    @Test
    fun getDetailMovie() {
        val movie_id = detailMovie.value?.id
        `when`(remote.getDetailMovie(movie_id.toString())).thenReturn(detailMovie)
        val movie = repository.getDetailMovie(movie_id.toString())
        Assert.assertNotNull(movie)
        Assert.assertEquals(detailMovie.value?.id, movie.value?.id)
        Assert.assertEquals(detailMovie.value?.original_title, movie.value?.original_title)
        Assert.assertEquals(detailMovie.value?.overview, movie.value?.overview)
        Assert.assertEquals(detailMovie.value?.genres, movie.value?.genres)
        Assert.assertEquals(detailMovie.value?.vote_average, movie.value?.vote_average)
        Assert.assertEquals(detailMovie.value?.tagline, movie.value?.tagline)
        Assert.assertEquals(detailMovie.value?.status, movie.value?.status)
    }

    @Test
    fun getListTvShow() {
        `when`(remote.getPopularTvShow()).thenReturn(DataDummyTest.generateDummyTvShow())
        val show = repository.getPopularTvShow()
        Mockito.verify(remote).getPopularTvShow()
        Assert.assertNotNull(show)
        Assert.assertEquals(1, show.value?.size)
    }

    @Test
    fun getDetailTvShow() {
        val tv_id = detailTvShow.value?.id
        `when`(remote.getDetailTvShow(tv_id.toString())).thenReturn(detailTvShow)
        val television = repository.getDetailTvShow(tv_id.toString())
        Assert.assertNotNull(television)
        Assert.assertEquals(detailTvShow.value?.id, television.value?.id)
        Assert.assertEquals(detailTvShow.value?.original_name, television.value?.original_name)
        Assert.assertEquals(
            detailTvShow.value?.original_language,
            television.value?.original_language
        )
        Assert.assertEquals(detailTvShow.value?.genres, television.value?.genres)
        Assert.assertEquals(detailTvShow.value?.overview, television.value?.overview)
        Assert.assertEquals(detailTvShow.value?.status, television.value?.status)
        Assert.assertEquals(detailTvShow.value?.vote_average, television.value?.vote_average)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Poster>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        repository.getFavoriteMovie()

        val poster =
            Resource.success(PagedListUtil.mockPagedList(DataDummyTest.generateDummyFavoriteMovies()))
        verify(local).getFavoriteMovie()
        Assert.assertNotNull(poster)
        assertEquals(posterResponses.size.toLong(), poster.data?.size?.toLong())

    }

    @Test
    fun getFavoriteTV() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Poster>
        `when`(local.getFavoriteTV()).thenReturn(dataSourceFactory)
        repository.getFavoriteTV()

        val poster =
            Resource.success(PagedListUtil.mockPagedList(DataDummyTest.generateDummyFavoriteTV()))
        verify(local).getFavoriteTV()
        Assert.assertNotNull(poster)
        assertEquals(posterResponses.size.toLong(), poster.data?.size?.toLong())
    }

    @Test
    fun addToFavoriteMovie() {
        val dummyMovie = DataDummyTest.generateDummyMovie()
        repository.addToFavoriteMovie(dummyMovie)
        verify(local, times(1)).addToFavoriteMovie(dummyMovie)
    }

    @Test
    fun removeFromFavoriteMovie() {
        val dummyMovie = DataDummyTest.generateDummyMovie()
        repository.removeMovieFavorite(dummyMovie.id)
        verify(local, times(1)).removeMovieFavorite(dummyMovie.id)
    }

    @Test
    fun addFavoriteTV() {
        val dummyTV = DataDummyTest.generateDummyTV()
        repository.addToFavoriteTV(dummyTV)
        verify(local, times(1)).addToFavoriteTV(dummyTV)
    }

    @Test
    fun removeFavoriteTV() {
        val dummyTV = DataDummyTest.generateDummyTV()
        repository.removeTVFavorite(dummyTV.id)
        verify(local, times(1)).removeTVFavorite(dummyTV.id)

    }
}