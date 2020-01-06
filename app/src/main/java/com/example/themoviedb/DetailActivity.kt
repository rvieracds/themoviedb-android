package com.example.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlin.math.roundToInt
import kotlin.math.truncate

class DetailActivity : AppCompatActivity() {
    lateinit var nameOfMovie: TextView
    lateinit var movieoverview: TextView
    lateinit var movieScore: TextView
    lateinit var imageView: ImageView
    lateinit var recyclerViewCast: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initCollapsingToolbar()

        imageView = findViewById(R.id.thumbnail_image_header)
        nameOfMovie = findViewById(R.id.title)
        movieScore = findViewById(R.id.userrating)
        movieoverview = findViewById(R.id.movieoverview)
        recyclerViewCast = findViewById(R.id.recycler_view_cast)

        val intent = intent
        if (intent.hasExtra("original_title")) {
            val thumbnail = getIntent().extras!!.getString("poster_path")
            val movieName = getIntent().extras!!.getString("original_title")
            val overview = getIntent().extras!!.getString("overview")
            var rating = getIntent().extras!!.getDouble("vote_average")

            rating *= 10
            rating = rating.roundToInt().toDouble()

            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.load)

            val url = "https://image.tmdb.org/t/p/w500$thumbnail"

            Glide.with(this)
                .load(url)
                .apply(requestOptions)
                .into(imageView)

            nameOfMovie.text = movieName
            movieoverview.text = overview
            movieScore.text = "$rating %"





            recyclerViewCast.setHasFixedSize(true)
            recyclerViewCast.adapter = CastAdapter(movie.credits?.cast ?: emptyList())
            recyclerViewCast.itemAnimator = DefaultItemAnimator()
            recyclerViewCast.layoutManager = LinearLayoutManager(baseContext)
            recyclerViewCast.isNestedScrollingEnabled = false







        } else {
            Toast.makeText(this, "No API Data", Toast.LENGTH_LONG).show()
        }
    }

    private fun initCollapsingToolbar() {
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbarLayout.isTitleEnabled = true
        collapsingToolbarLayout.title = ""

        val appBarLayout = findViewById<AppBarLayout>(R.id.appbar)
        appBarLayout.setExpanded(true)

        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.title = getString(R.string.movie_details)
                    collapsingToolbarLayout.setCollapsedTitleTextColor(resources.getColor(R.color.colorAccent))
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.title = ""
                    isShow = false
                }
            }
        })
    }
}
