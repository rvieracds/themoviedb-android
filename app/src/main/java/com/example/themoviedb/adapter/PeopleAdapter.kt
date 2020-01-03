package com.example.themoviedb.adapter

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.R
import com.example.themoviedb.model.KnownFor
import com.example.themoviedb.model.People

class PeopleAdapter(context: Context, people: List<People>) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private var context: Context
    private var people: List<People> = ArrayList()

    init {
        this.people = people
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.people_card, parent, false)
        return ViewHolder(itemView = v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = people[position].getName()
        val knownForList = people[position].getKnownFor()

        var knownForText = "<b>Known for: </b>"
        for ((i, item) in knownForList.withIndex()) {
            if (i != knownForList.lastIndex){
                knownForText += item.getOriginalTitle() + ", "
            } else {
                knownForText += item.getOriginalTitle()
            }
        }

        holder.knownFor.text = HtmlCompat.fromHtml(knownForText, HtmlCompat.FROM_HTML_MODE_LEGACY);

        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.load)
        val url = "https://image.tmdb.org/t/p/w500" + people[position].getProfilePath()

        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(holder.profilePic)

    }

    override fun getItemCount(): Int {
        return people.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById<View>(R.id.name) as TextView
        var knownFor: TextView = itemView.findViewById<View>(R.id.knownFor) as TextView
        var profilePic: ImageView = itemView.findViewById<View>(R.id.profilePic) as ImageView

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