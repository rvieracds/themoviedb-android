package com.example.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviedb.R
import com.example.themoviedb.model.Movie

class MoviesAdapter(context: Context, movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var context: Context
    private var movies: List<Movie> = ArrayList()
    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    init {
        this.movies = movies
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(movies[position], listener)
        holder.title.setText(movies[position].originalTitle)
        val vote = movies[position].rating.toString()
        holder.userrating.setText(vote)

        Glide.with(context).load(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder() : RecyclerView.ViewHolder() {
        lateinit var title: TextView
        lateinit var userrating: TextView
        lateinit var thumbnail: ImageView

        fun ViewHolder(view: View) {
            super(view)
            title = view.findViewById(R.id.title)
            userrating = view.findViewById(R.id.userrating)
            thumbnail = view.findViewById(R.id.thumbnail)
        }
    }
}