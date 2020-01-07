package com.example.themoviedb.activities


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.BuildConfig
import com.example.themoviedb.fragments.MovieInfoFragment
import com.example.themoviedb.R
import com.example.themoviedb.fragments.RateMovieFragment
import com.example.themoviedb.adapter.CastAdapter
import com.example.themoviedb.api.Client
import com.example.themoviedb.api.Service
import com.example.themoviedb.model.Cast
import com.example.themoviedb.model.MovieCreditResponse
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.content_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt


class DetailActivity : AppCompatActivity() {
    private lateinit var nameOfMovie: TextView
    private lateinit var movieoverview: TextView
    private lateinit var movieScore: TextView
    private lateinit var imageView: ImageView
    private lateinit var recyclerViewCast: RecyclerView
    private lateinit var adapter: CastAdapter
    private var cast: List<Cast> = ArrayList()
    private lateinit var llProgressBar: LinearLayout


    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.main_content, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

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
        llProgressBar = findViewById(R.id.llProgressBar)

        llProgressBar!!.visibility = View.VISIBLE

        val intent = intent
        if (intent.hasExtra("original_title")) {
            val movieId = getIntent().extras!!.getInt("id")!!
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

            loadJSON(movieId)

            val layoutManager = LinearLayoutManager(baseContext)
            layoutManager.orientation = RecyclerView.HORIZONTAL
            recyclerViewCast.layoutManager = layoutManager


            infoButton.setOnClickListener {
                val fragment =
                    MovieInfoFragment()
                addFragment(fragment)
            }

            rateButton.setOnClickListener {
                val fragment =
                    RateMovieFragment()
                addFragment(fragment)
            }

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
                    collapsingToolbarLayout.setCollapsedTitleTextColor(resources.getColor(
                        R.color.colorAccent
                    ))
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.title = ""
                    isShow = false
                }
            }
        })
    }

    private fun loadJSON(movieId: Int) {
        try{
            if(BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(applicationContext, "Obtain the API KEY first", Toast.LENGTH_SHORT).show();
                llProgressBar.visibility = View.GONE
                return
            }

            val client = Client()
            val apiService = client.getClient()!!.create(Service::class.java)
            val call = apiService.getMovieCredits(movieId,
                BuildConfig.THE_MOVIE_DB_API_TOKEN
            )

            call.enqueue(object : Callback<MovieCreditResponse> {
                override fun onResponse(call: Call<MovieCreditResponse>, response: Response<MovieCreditResponse>) =
                    if(response.isSuccessful) {
                        val cast: List<Cast> = response.body()!!.getCast()
                        adapter = CastAdapter(baseContext , cast)
                        recyclerViewCast.adapter = adapter
                        recyclerViewCast.smoothScrollToPosition(0)
                        adapter.notifyDataSetChanged()

                        llProgressBar.visibility = View.GONE
                    } else {
                        Toast.makeText(applicationContext, "Error fetching data!", Toast.LENGTH_SHORT).show();
                    }

                override fun onFailure(call: Call<MovieCreditResponse>, t: Throwable) {
                    Log.d("Error PEPE 0", t.message)
                    Toast.makeText(applicationContext, "Error fetching data!", Toast.LENGTH_SHORT).show();
                }
            })
        } catch(e: Exception) {
            Log.d("Error z", e.message)
            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
