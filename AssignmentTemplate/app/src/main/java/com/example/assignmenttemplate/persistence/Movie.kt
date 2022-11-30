package com.example.assignmenttemplate.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "Movie")
data class Movie(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title") val movieTitle: String,
    @ColumnInfo(name = "release_year") val movieReleaseYear: String,
    @ColumnInfo(name = "director") val movieDirector: String,
    @ColumnInfo(name = "cast") val movieCast: ArrayList<String>,
    @ColumnInfo(name = "image_reference") val movieImage: Int

)

class CastTypeConverter {
    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        val listType = object: TypeToken<ArrayList<String>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>): String {
        return Gson().toJson(list)
    }
}