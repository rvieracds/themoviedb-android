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

