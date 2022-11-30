package com.example.assignmenttemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.assignmenttemplate.persistence.MoviePersistence
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

const val MOVIE_INFO = "com.example.androidApp.MOVIEINFORMATION"

class MovieDetails : AppCompatActivity() {
    lateinit var database: MoviePersistence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        database = MoviePersistence.getAppDatabase(this)!!

        val movieID = intent.getStringExtra(MOVIE_INFO)?.toInt()
        if (movieID != null) {
            fetchMovie(movieID)
        }
    }

    fun fetchMovie(movieID: Int) = runBlocking {
        launch {
            val movie = database.movieDAO().loadByID(movieID)
            if (movie != null) {
                val movieImageView: ImageView = findViewById<ImageView>(R.id.imageView)
                movieImageView.setImageResource(movie.movieImage)
                val movieTitleText = findViewById<TextView>(R.id.movieTitleText).apply {
                    text = movie.movieTitle
                }
                val cast = movie.movieCast.toString().replace(",", "\n").replace("[", "")
                val movieInformation = "Director: " + movie.movieDirector + "\n " +
                        "Released: " + movie.movieReleaseYear + "\n " +
                        "Cast:\n" + cast + "\n"

                val movieInformationTextView = findViewById<TextView>(R.id.movieDetails).apply {
                    text = movieInformation
                }
                Log.i("MovieFetched", movie.movieTitle)
            } else {
                Log.e("MovieDetails", "No movie with id " + movieID)
                finish()
            }
        }
    }
}