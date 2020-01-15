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
import com.example.themoviedb.model.Cast

class CastAdapter(context: Context, cast: List<Cast>) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private var context: Context
    private var cast: List<Cast> = ArrayList()

    init {
        this.cast = cast
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.cast_card, parent, false)
        return ViewHolder(itemView = v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.actorName.text = cast[position].name

        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.load)
        val url = "https://image.tmdb.org/t/p/w500" + cast[position].profilePath

        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(holder.actorPic)

    }

    override fun getItemCount(): Int {
        return cast.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var actorName: TextView = itemView.findViewById<View>(R.id.actorName) as TextView
        var actorPic: ImageView = itemView.findViewById<View>(R.id.actorPic) as ImageView

//        init {
//            itemView.setOnClickListener { view ->
//                val pos = adapterPosition
//                if (pos != RecyclerView.NO_POSITION) {
//                    val clickedDataItem = cast[pos]
//                    val data = Bundle()
//
////                    data.putInt("id", cast[pos].getId())
////                    data.putString("original_title", cast[pos].getOriginalTitle())
////                    data.putString("poster_path", cast[pos].getPosterPath())
////                    data.putString("overview",  cast[pos].getOverview())
////                    data.putDouble("vote_average", cast[pos].getVoteAverage())
////                    data.putString("release_date", cast[pos].getReleaseDate())
//
//                    val activity = view.context as AppCompatActivity
//                    Navigation.findNavController(activity, R.id.my_nav_host_fragment).navigate(R.id.MovieCastCrewFragment, data)
//                    Toast.makeText(view.context, "You clicked" + clickedDataItem.name, Toast.LENGTH_LONG).show()
//                }
//            }
//        }

    }
}

