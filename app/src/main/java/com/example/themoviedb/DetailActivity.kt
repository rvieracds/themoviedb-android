package com.example.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class DetailActivity : AppCompatActivity() {
    lateinit var nameOfMovie: TextView
    lateinit var movieoverview: TextView
    lateinit var userrating: TextView
    lateinit var releaseDate: TextView
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initCollapsingToolbar()

        imageView = findViewById(R.id.thumbnail_image_header)
        nameOfMovie = findViewById(R.id.title)
        userrating = findViewById(R.id.userrating)
        movieoverview = findViewById(R.id.movieoverview)


        val intent = intent
        if (intent.hasExtra("original_title")) {
            val thumbnail = getIntent().extras!!.getString("poster_path")
            val movieName = getIntent().extras!!.getString("original_title")
            val overview = getIntent().extras!!.getString("overview")
            val rating = getIntent().extras!!.getString("vote_average")
            val dateOfRelease = getIntent().extras!!.getString("release_date")

            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.load)

            val url = "https://image.tmdb.org/t/p/w500$thumbnail"

            Glide.with(this)
                .load(url)
                .apply(requestOptions)
                .into(imageView)

            nameOfMovie.text = movieName
            movieoverview.text = overview
            userrating.text = rating
//            releaseDate.text = dateOfRelease

        } else {
            Toast.makeText(this, "No API Data", Toast.LENGTH_LONG).show()
        }
    }

    private fun initCollapsingToolbar() {
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbarLayout.title = " "
        val appBarLayout = findViewById<AppBarLayout>(R.id.appbar)
        appBarLayout.setExpanded(true)

        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.title = getString(R.string.movie_details)
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.title = " "
                    isShow = false
                }
            }
        })
    }
}
