package com.mellagusty.movieapppopcorn.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mellagusty.movieapppopcorn.data.remote.Poster

@Dao
interface PopWatchDao {

    //Movies
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovieFavorite(favoriteMovie: MoviePoster)

    @Query("SELECT * FROM movie_favorite ORDER BY id ASC")
    fun getMovieFavorite(): DataSource.Factory<Int, Poster>

    @Query("SELECT count(*) FROM movie_favorite WHERE movie_favorite.id = :id")
    suspend fun checkMovieFavorite(id: String): Int

    @Query("DELETE FROM movie_favorite WHERE movie_favorite.id = :id")
    suspend fun removeMovieFavorite(id: String): Int


    //Television
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTVFavorite(favoriteTV: TvPoster)

    @Query("SELECT * FROM serial_favorite ORDER BY id ASC")
    fun getTVFavorite(): DataSource.Factory<Int, Poster>

    @Query("SELECT count(*) FROM serial_favorite WHERE serial_favorite.id = :id")
    suspend fun checkTVFavorite(id: String): Int

    @Query("DELETE FROM serial_favorite WHERE serial_favorite.id = :id")
    suspend fun removeTVFavorite(id: String)


}