package com.example.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.R
import com.example.themoviedb.model.KnownFor

class KnownForListAdapter(context: Context, knownFor: ArrayList<KnownFor>) : RecyclerView.Adapter<KnownForListAdapter.ViewHolder>() {

    private var context: Context
    private var knownFor: ArrayList<KnownFor> = ArrayList()

    init {
        this.knownFor = knownFor
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.generic_card, parent, false)
        return ViewHolder(itemView = v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = knownFor[position].getOriginalTitle()
        holder.subtitle.text = knownFor[position].getOverview()

        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.load)
        val url = "https://image.tmdb.org/t/p/w500" + knownFor[position].getPosterPath()

        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(holder.image)

    }

    override fun getItemCount(): Int {
        return knownFor.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById<View>(R.id.title) as TextView
        var subtitle: TextView = itemView.findViewById<View>(R.id.subtitle) as TextView
        var image: ImageView = itemView.findViewById<View>(R.id.image) as ImageView
    }
}