package com.example.themoviedb.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.R
import com.example.themoviedb.R.drawable
import com.example.themoviedb.model.Movie
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.activity_main.view.*

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
                    val data = Bundle()

                    data.putInt("id", movies[pos].getId())
                    data.putString("original_title", movies[pos].getOriginalTitle())
                    data.putString("poster_path", movies[pos].getPosterPath())
                    data.putString("overview",  movies[pos].getOverview())
                    data.putDouble("vote_average", movies[pos].getVoteAverage())
                    data.putString("release_date", movies[pos].getReleaseDate())

                    val activity = view.context as AppCompatActivity
                    Navigation.findNavController(activity, R.id.my_nav_host_fragment).navigate(R.id.MovieDetailFragment, data)
                    Toast.makeText(view.context, "You clicked" + clickedDataItem.getOriginalTitle(), Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}