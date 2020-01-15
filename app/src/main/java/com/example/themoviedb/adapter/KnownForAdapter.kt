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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.R
import com.example.themoviedb.model.KnownFor

class KnownForAdapter(context: Context, knownFor: ArrayList<KnownFor>) : RecyclerView.Adapter<KnownForAdapter.ViewHolder>() {

    private var context: Context
    private var knownFor: ArrayList<KnownFor> = ArrayList()

    init {
        this.knownFor = knownFor
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.known_for_card, parent, false)
        return ViewHolder(itemView = v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.actorName.text = knownFor[position].getOriginalTitle()

        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.load)
        val url = "https://image.tmdb.org/t/p/w500" + knownFor[position].getPosterPath()

        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(holder.moviePic)

    }

    override fun getItemCount(): Int {
        return knownFor.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var actorName: TextView = itemView.findViewById<View>(R.id.movieName) as TextView
        var moviePic: ImageView = itemView.findViewById<View>(R.id.moviePic) as ImageView

//        init {
//            itemView.setOnClickListener { view ->
//                val pos = adapterPosition
//                if (pos != RecyclerView.NO_POSITION) {
//                    val clickedDataItem = knownFor[pos]
//                    val data = Bundle()
//
////                    data.putInt("id", cast[pos].getId())
////                    data.putString("original_title", cast[pos].getOriginalTitle())
////                    data.putString("poster_path", cast[pos].getPosterPath())
////                    data.putString("overview",  cast[pos].getOverview())
////                    data.putDouble("vote_average", cast[pos].getVoteAverage())
////                    data.putString("release_date", cast[pos].getReleaseDate())
//
////                    val activity = view.context as AppCompatActivity
////                    Navigation.findNavController(activity, R.id.my_nav_host_fragment).navigate(R.id.PeopleKnownForFragment, data)
////                    Toast.makeText(view.context, "You clicked" + clickedDataItem.getOriginalTitle(), Toast.LENGTH_LONG).show()
//                }
//            }
//        }

    }
}

