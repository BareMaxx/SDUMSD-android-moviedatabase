package com.example.assignmenttemplate

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmenttemplate.persistence.Movie

class MovieAdapter(private val movieData : List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(movieItem : View): RecyclerView.ViewHolder(movieItem) {
        val movieImage: ImageView = movieItem.findViewById(R.id.movieImage)
        val id : TextView = movieItem.findViewById(R.id.id)
        val movieTitle: TextView = movieItem.findViewById(R.id.movieTitle)
        val movieReleaseYear: TextView = movieItem.findViewById(R.id.movieReleaseYear)
        val movieDirector: TextView = movieItem.findViewById(R.id.movieDirector)
        val movieCast: TextView = movieItem.findViewById(R.id.movieCast)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieImage.setImageResource(movieData[position].movieImage)
        holder.id.text = movieData[position].id.toString()
        holder.movieTitle.text = movieData[position].movieTitle
        holder.movieReleaseYear.text = movieData[position].movieReleaseYear
        holder.movieDirector.text = movieData[position].movieDirector
        holder.movieCast.text = movieData[position].movieCast.toString().replace(",", "\n").replace("[", "")
    }

    override fun getItemCount(): Int {
        return movieData.size
    }
}