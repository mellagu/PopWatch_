package com.mellagusty.movieapppopcorn.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "serial_favorite")
data class TvPoster(
    @PrimaryKey
    val id: String,
    val first_air_date: String,
    val poster_path: String,
    val original_name: String
) : Serializable {
    val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}