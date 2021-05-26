package com.mellagusty.movieapppopcorn.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviePosterDetail(

    @field: SerializedName("id")
    val id: Int,

    @field: SerializedName("poster_path")
    val poster_path: String,

    @field: SerializedName("original_title")
    val original_title: String? = null,

    @field: SerializedName("overview")
    val overview: String? = null,

    @field: SerializedName("original_language")
    val original_language: String? = null,

    @field: SerializedName("genres")
    val genres: List<GenreModel>,

    @field: SerializedName("vote_average")
    val vote_average: Double? = null,

    @field: SerializedName("tagline")
    val tagline: String? = null,

    @field: SerializedName("status")
    val status: String? = null

) : Parcelable{
    val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}


@Parcelize
class GenreModel(
    @field: SerializedName("name")
    val name: String,

    @field: SerializedName("id")
    val id: Int
) : Parcelable
