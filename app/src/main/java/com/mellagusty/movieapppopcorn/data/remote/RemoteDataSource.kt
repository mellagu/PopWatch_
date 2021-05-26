package com.mellagusty.movieapppopcorn.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mellagusty.movieapppopcorn.resource.EspressoIdlingResource
import com.mellagusty.movieapppopcorn.data.remote.api.Api
import com.mellagusty.movieapppopcorn.data.remote.api.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiHelper: Api) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstances(api: Api): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(api).apply { instance = this }
            }
    }

    fun getNowPlayingMovie(): LiveData<List<Poster>> {
        val movies = MutableLiveData<List<Poster>>()
        //Do increment Idling(testing)
        try {
            EspressoIdlingResource.increment()
        } catch (e: Throwable) {
        }

        apiHelper.getNowPlayingMovie()
            .enqueue(object : Callback<DataResponse> {
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    t.message?.let { Log.d("failure", it) }
                    EspressoIdlingResource.decrement()
                }

                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    //Do decrement Idling(testing)
                    try {
                        EspressoIdlingResource.decrement()
                    } catch (e: Throwable) {
                    }

                    if (response.isSuccessful) {
                        movies.postValue(response.body()?.results as ArrayList<Poster>?)
                    }
                }

            })
        return movies
    }

     fun getDetailMovie(movie_id: String): LiveData<MoviePosterDetail> {
        val detailMovie = MutableLiveData<MoviePosterDetail>()
        //Do increment Idling(testing)
        try {
            EspressoIdlingResource.increment()
        } catch (e: Throwable) {
        }

         apiHelper.getDetailMovie(movie_id)
            .enqueue(object : Callback<MoviePosterDetail> {
                override fun onFailure(call: Call<MoviePosterDetail>, t: Throwable) {
                    t.message?.let { Log.d("failure", it) }
                    //Do decrement Idling(testing)
                    EspressoIdlingResource.decrement()
                }

                override fun onResponse(
                    call: Call<MoviePosterDetail>,
                    response: Response<MoviePosterDetail>
                ) {

                    //Do decrement Idling(testing)
                    try {
                        EspressoIdlingResource.decrement()
                    } catch (e: Throwable) {
                    }

                    if (response.isSuccessful) {
                        detailMovie.postValue(response.body())
                    }
                }

            })
        return detailMovie
    }

     fun getPopularTvShow(): LiveData<List<Poster>> {
        val tvseries = MutableLiveData<List<Poster>>()
        //Do increment Idling(testing)
        try {
            EspressoIdlingResource.increment()
        } catch (e: Throwable) {
        }

         apiHelper.getPopularTvShow()
            .enqueue(object : Callback<DataResponse> {
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    t.message?.let { Log.d("failure", it) }
                    //Do decrement Idling(testing)
                    EspressoIdlingResource.decrement()
                }

                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    //Do decrement Idling(testing)
                    try {
                        EspressoIdlingResource.decrement()
                    } catch (e: Throwable) {
                    }

                    if (response.isSuccessful) {
                        tvseries.postValue(response.body()?.results as ArrayList<Poster>?)
                    }
                }

            })
        return tvseries
    }

     fun getDetailTvShow(tv_id: String): LiveData<TvShowPosterDetail> {
        val tvdetail = MutableLiveData<TvShowPosterDetail>()
        //Do increment Idling(testing)
        try {
            EspressoIdlingResource.increment()
        } catch (e: Throwable) {
        }

        apiHelper.getDetailTvShow(tv_id)
            .enqueue(object : Callback<TvShowPosterDetail> {
                override fun onFailure(call: Call<TvShowPosterDetail>, t: Throwable) {
                    t.message?.let { Log.d("failure", it) }
                    //Do decrement Idling(testing)
                    EspressoIdlingResource.decrement()
                }

                override fun onResponse(
                    call: Call<TvShowPosterDetail>,
                    response: Response<TvShowPosterDetail>
                ) {
                    //Do decrement Idling(testing)
                    try {
                        EspressoIdlingResource.decrement()
                    } catch (e: Throwable) {
                    }

                    if (response.isSuccessful) {
                        tvdetail.postValue(response.body())
                    }
                }

            })
        return tvdetail
    }

}