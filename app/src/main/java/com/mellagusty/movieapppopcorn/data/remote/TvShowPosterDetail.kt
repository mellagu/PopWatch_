package com.mellagusty.movieapppopcorn.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowPosterDetail(

    @field: SerializedName("id")
    val id :Int,

    @field: SerializedName("original_name")
    val original_name: String,

    @field: SerializedName("original_language")
    val original_language: String,

    @field: SerializedName("overview")
    val overview: String? = null,

    @field: SerializedName("status")
    val status: String,

    @field: SerializedName("number_of_episodes")
    val number_of_episodes: Int? = null,

    @field: SerializedName("vote_average")
    val vote_average: Double,

    @field: SerializedName("poster_path")
    val poster_path: String,

    @field: SerializedName("genres")
    val genres: List<GenreModels>,

    @field: SerializedName("type")
    val type: String,

    @field: SerializedName("tagline")
    val tagline: String? = null

    ) : Parcelable {
    val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}

@Parcelize
class GenreModels(
    @field: SerializedName("name")
    val name: String,

    @field: SerializedName("id")
    val id: Int
) : Parcelable
