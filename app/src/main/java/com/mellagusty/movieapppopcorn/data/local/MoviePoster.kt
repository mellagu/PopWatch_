package com.mellagusty.movieapppopcorn.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movie_favorite")
data class MoviePoster(
    @PrimaryKey
    val id: String,
    val release_date: String,
    val poster_path: String,
    val original_title: String
) : Serializable {
    val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}