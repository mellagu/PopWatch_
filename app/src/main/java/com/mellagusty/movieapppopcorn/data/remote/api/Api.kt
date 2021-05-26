package com.mellagusty.movieapppopcorn.data.remote.api

import com.mellagusty.movieapppopcorn.BuildConfig
import com.mellagusty.movieapppopcorn.data.remote.MoviePosterDetail
import com.mellagusty.movieapppopcorn.data.remote.TvShowPosterDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    companion object {
        const val API_KEY = BuildConfig.POPWATCH_TOKEN
    }

    //get Movies
    @GET("movie/now_playing?api_key=$API_KEY")
    fun getNowPlayingMovie(
    ): Call<DataResponse>

    //get Detail Movies
    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getDetailMovie(
        @Path("movie_id") query: String
    ): Call<MoviePosterDetail>

    //TvShow
    @GET("tv/popular?api_key=$API_KEY")
    fun getPopularTvShow(
    ): Call<DataResponse>

    //get Detail TvShow
    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getDetailTvShow(
        @Path("tv_id") query: String
    ): Call<TvShowPosterDetail>


}