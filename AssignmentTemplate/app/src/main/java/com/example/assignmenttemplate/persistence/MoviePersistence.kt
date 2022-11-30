package com.example.assignmenttemplate.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(Movie::class), version = 1)
@TypeConverters(CastTypeConverter::class)
abstract class MoviePersistence : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO

    companion object {
        private var INSTANCE: MoviePersistence? = null

        fun getAppDatabase(context: Context): MoviePersistence? {
            if (INSTANCE == null) {
                INSTANCE = databaseBuilder(context.applicationContext, MoviePersistence::class.java, "MoviePersistence2")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}