package com.mellagusty.movieapppopcorn.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MoviePoster::class,TvPoster::class], version = 1, exportSchema = false)

abstract class PopWatchDatabase: RoomDatabase() {
    
    abstract fun favoritePopWatchDao() : PopWatchDao
    
    companion object{
        @Volatile
        private var INSTANCE: PopWatchDatabase? = null

        //We're going use the same instance for our database
        fun getDatabase(context: Context): PopWatchDatabase {
            //if instance is not null then returning the instance
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PopWatchDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }

}