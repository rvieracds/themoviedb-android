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
//                    val clickedDataItem = people[pos]
//                    val intent = Intent(context, DetailActivity::class.java)
//                    intent.putExtra("original_title", people[pos].getOriginalTitle())
//                    intent.putExtra("poster_path", people[pos].getPosterPath())
//                    intent.putExtra("overview", people[pos].getOverview())
//                    intent.putExtra("vote_average", people[pos].getVoteAverage())
//                    intent.putExtra("release_date", people[pos].getReleaseDate())
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    context.startActivity(intent)
//                    Toast.makeText(view.context, "You clicked" + clickedDataItem.getOriginalTitle(), Toast.LENGTH_LONG).show()
//                }
//            }
//        }

    }
}

