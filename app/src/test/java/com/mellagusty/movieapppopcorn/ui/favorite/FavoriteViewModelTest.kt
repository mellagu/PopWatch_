package com.mellagusty.movieapppopcorn.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.mellagusty.movieapppopcorn.data.DataDummyTest
import com.mellagusty.movieapppopcorn.data.remote.Poster
import com.mellagusty.movieapppopcorn.data.remote.Repository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<Poster>>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(repository)
    }

    //Testing

    @Test
    fun getFavoriteMovie() {
        val expected = MutableLiveData<PagedList<Poster>>()
        expected.value = PagedTestDataSources.snapshot(DataDummyTest.generateDummyFavoriteMovies())

        Mockito.`when`(repository.getFavoriteMovie()).thenReturn(expected)

        viewModel.getFavoriteMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteMovie().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun getFavoriteTV() {
        val expected = MutableLiveData<PagedList<Poster>>()
        expected.value = PagedTestDataSources.snapshot(DataDummyTest.generateDummyFavoriteTV())

        Mockito.`when`(repository.getFavoriteTV()).thenReturn(expected)

        viewModel.getFavoriteTV().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteTV().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    class PagedTestDataSources private constructor(private val items: List<Poster>) :
        PositionalDataSource<Poster>() {
        companion object {
            fun snapshot(items: List<Poster> = listOf()): PagedList<Poster> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }

        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Poster>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Poster>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }


    }
}