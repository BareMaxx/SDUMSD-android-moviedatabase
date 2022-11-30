package com.example.assignmenttemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.assignmenttemplate.persistence.Movie
import com.example.assignmenttemplate.persistence.MoviePersistence
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var database: MoviePersistence

    private lateinit var numbers: EditText
    private lateinit var attributes: TextView

    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = MoviePersistence.getAppDatabase(this)!!
        if(database.movieDAO().getAll().isEmpty()) {
            seedDatabase()
        }

        movieAdapter = MovieAdapter(database.movieDAO().getAll())
        var movieRecyclerView: RecyclerView = findViewById(R.id.movieView)
        movieRecyclerView.setHasFixedSize(true)
        var layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        movieRecyclerView.layoutManager = layoutManager
        movieRecyclerView.adapter = movieAdapter
    }

    fun seedDatabase() = runBlocking {
        launch {
            Log.i("Database", "Seeding Database")
            var movie1 = Movie(
                0,
                "Django Unchained",
                "2012",
                "Quentin Tarantino",
                arrayListOf("Jamie Foxx","Leonardo DiCaprio", "Christoph Waltz"),
                R.drawable.djangounchained
            )
            var movie2 = Movie(
                0,
                "Shrek",
                "2001",
                "Andrew Adamson",
                arrayListOf("Mike Myers", "Eddie Murhpy", "Cameron Diaz"),
                R.drawable.shrek
            )
            var movie3 = Movie(
                0,
                "Pulp Fiction",
                "1994",
                "Quentin Tarantino",
                arrayListOf("Uma Thurman", "Samuel L. Jackson", "John Travolta"),
                R.drawable.pulpfiction
            )
            var movie4 = Movie(
                0,
                "Get Out",
                "2017",
                "Jordan Peele",
                arrayListOf("Daniel Kaluuya", "Allison Williams", "LaKeith Stanfield"),
                R.drawable.getout
            )
            var movie5 = Movie(
                0,
                "Saving Private Ryan",
                "1998",
                "Steven Spielberg",
                arrayListOf("Tom Hanks", "Matt Damon", "Vin Diesel"),
                R.drawable.ryan
            )
            var movie6 = Movie(
                0,
                "Gladiator",
                "2000",
                "Ridley Scott",
                arrayListOf("Russel Crowe", "Joaquin Phoenix", "Connie Nielsen"),
                R.drawable.gladiator
            )
            database.movieDAO().insert(movie1)
            database.movieDAO().insert(movie2)
            database.movieDAO().insert(movie3)
            database.movieDAO().insert(movie4)
            database.movieDAO().insert(movie5)
            database.movieDAO().insert(movie6)
        }
    }

    fun showMovieFromSearch(view: View) {
        val movieID = findViewById<EditText>(R.id.movieIDSearch).text.toString()
        if(movieID == "") {
            Log.e("MovieSearch", "Empty movie id, can not search")
            return
        }
        Log.i("MovieSearch", "Searching for movie with id $movieID")
        val intent = Intent(this, MovieDetails::class.java).apply {
            putExtra(MOVIE_INFO, movieID)
        }
        startActivity(intent)
    }
    fun showMovie(view: View){
        val movieID = findViewById<TextView>(R.id.id).text.toString()
        if(movieID == "") {
            Log.e("MovieSearch", "Empty movie id, can not search")
            return
        }
        Log.i("MovieSearch", "Searching for movie with id $movieID")
        val intent = Intent(this, MovieDetails::class.java).apply {
            putExtra(MOVIE_INFO, movieID)
        }
        startActivity(intent)
    }
}