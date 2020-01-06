package com.example.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.R


class CastAdapter(context: Context, cast: List<Actor>) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private var context: Context
    private var cast: List<Actor> = ArrayList()

    init {
        this.cast = cast
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.cast_card, parent, false)
        return ViewHolder(itemView = v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.actorName.text = cast[position].getName()

        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.load)
        val url = "https://image.tmdb.org/t/p/w500" + cast[position].getActorPath()

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




//class CastAdapter(private var cast: List<MovieInfoCast>) : RecyclerView.Adapter<CastAdapter.MovieViewHolder>() {
//
//    fun addAll(cast: List<MovieInfoCast>) {
//        this.cast = cast
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_detail_actor_list_element, parent, false)
//
//        return MovieViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        val actor = cast[position]
//        holder.name.text = actor.name
//        holder.character.text = actor.character
//
//
//
//
//
//
//        holder.name.text = people[position].getName()
//        val knownForList = people[position].getKnownFor()
//
//        var knownForText = "<b>Known for: </b>"
//        for ((i, item) in knownForList.withIndex()) {
//            if (i != knownForList.lastIndex){
//                knownForText += item.getOriginalTitle() + ", "
//            } else {
//                knownForText += item.getOriginalTitle()
//            }
//        }
//
//        holder.knownFor.text = HtmlCompat.fromHtml(knownForText, HtmlCompat.FROM_HTML_MODE_LEGACY);
//
//        val requestOptions = RequestOptions()
//        requestOptions.placeholder(R.drawable.load)
//        val url = "https://image.tmdb.org/t/p/w500" + people[position].getProfilePath()
//
//        Glide.with(context)
//            .load(url)
//            .apply(requestOptions)
//            .into(holder.profilePic)
//    }
//
//    override fun getItemCount(): Int {
//        return cast.size
//    }
//
//    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var character: TextView = itemView.findViewById(R.id.actor_list_item_character) as TextView
//        var name: TextView = itemView.findViewById(R.id.actorName) as TextView
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//}