package com.mellagusty.movieapppopcorn.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mellagusty.movieapppopcorn.data.DataDummyTest
import com.mellagusty.movieapppopcorn.data.remote.Repository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel

    @Mock
    private lateinit var repository: Repository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        tvShowViewModel = TvShowViewModel(repository)
    }

    //ViewModel ini sudah mengambil data dari API
    @Test
    fun getListTV() {
        Mockito.`when`(repository.getPopularTvShow())
            .thenReturn(DataDummyTest.generateDummyTvShow())
        tvShowViewModel.setPopularTvShow()
        val tvlist = tvShowViewModel.getPopularTvShow()
        Mockito.verify(repository).getPopularTvShow()
        Assert.assertNotNull(tvlist)
        Assert.assertEquals(1, tvlist.value?.size)
    }

    @Test
    fun getDetailTV() {
        val detailTvShow = DataDummyTest.generateDummyDetailTvShow()
        val tv_id = detailTvShow.value?.id
        Mockito.`when`(repository.getDetailTvShow(tv_id.toString())).thenReturn(detailTvShow)
        tvShowViewModel.setDetailTvShow(tv_id.toString())
        val television = tvShowViewModel.getDetailTVShow()
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
    fun addToFavoriteTV(){
        val dummyFavoriteTV = DataDummyTest.generateDummyTV()
        tvShowViewModel.addToFavoriteTV(dummyFavoriteTV)
        Mockito.verify(repository, Mockito.times(1)).addToFavoriteTV(dummyFavoriteTV)
    }

    @Test
    fun removeFromFavoriteTV(){
        val dummyFavoriteTV = DataDummyTest.generateDummyTV()
        tvShowViewModel.removeTVFavorite(dummyFavoriteTV.id)
        Mockito.verify(repository, Mockito.times(1)).removeTVFavorite(dummyFavoriteTV.id)

    }

}