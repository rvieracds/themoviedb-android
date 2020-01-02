package com.example.themoviedb.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.themoviedb.DetailActivity
import com.example.themoviedb.R
import com.example.themoviedb.R.drawable
import com.example.themoviedb.model.Movie

class MoviesAdapter(context: Context, movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var context: Context
    private var movies: List<Movie> = ArrayList()

    init {
        this.movies = movies
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(itemView = v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = movies[position].getOriginalTitle()
        holder.overview.text = movies[position].getOverview()
        holder.releaseDate.text = movies[position].getReleaseDate()
        val vote = movies[position].getVoteAverage().toString()
        holder.userrating.text = vote

        val requestOptions = RequestOptions()
        requestOptions.placeholder(drawable.load)
        val url = "https://image.tmdb.org/t/p/w500" + movies[position].getPosterPath()

        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(holder.thumbnail)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView
        var releaseDate: TextView
        var userrating: TextView
        var overview: TextView
        var thumbnail: ImageView

        init {
            title = itemView.findViewById<View>(R.id.title) as TextView
            releaseDate = itemView.findViewById<View>(R.id.releaseDate) as TextView
            userrating = itemView.findViewById<View>(R.id.userrating) as TextView
            overview = itemView.findViewById<View>(R.id.overview) as TextView
            thumbnail = itemView.findViewById<View>(R.id.thumbnail) as ImageView

            itemView.setOnClickListener { view ->
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val clickedDataItem = movies[pos]
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("original_title", movies[pos].getOriginalTitle())
                    intent.putExtra("poster_path", movies[pos].getPosterPath())
                    intent.putExtra("overview", movies[pos].getOverview())
                    intent.putExtra("vote_average", movies[pos].getVoteAverage())
                    intent.putExtra("release_date", movies[pos].getReleaseDate())
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    Toast.makeText(view.context, "You clicked" + clickedDataItem.getOriginalTitle(), Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}