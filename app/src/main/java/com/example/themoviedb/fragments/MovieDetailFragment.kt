package com.example.themoviedb.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.BuildConfig
import com.example.themoviedb.R
import com.example.themoviedb.adapter.CastAdapter
import com.example.themoviedb.adapter.CastListAdapter
import com.example.themoviedb.adapter.CrewListAdapter
import com.example.themoviedb.api.Client
import com.example.themoviedb.api.Service
import com.example.themoviedb.model.Cast
import com.example.themoviedb.model.Crew
import com.example.themoviedb.model.MovieCreditResponse
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt
import androidx.appcompat.app.AppCompatActivity as AppCompatActivity1


class MovieDetailFragment : Fragment() {

    private lateinit var nameOfMovie: TextView
    private lateinit var movieoverview: TextView
    private lateinit var movieScore: TextView
    private lateinit var imageView: ImageView
    private lateinit var recyclerViewCast: RecyclerView
    private lateinit var adapter: CastAdapter
    private lateinit var infoButton: ImageView
    private lateinit var rateButton: TextView
    private lateinit var movieContentDetail: LinearLayout
    private lateinit var viewAll: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        val data = arguments

        initCollapsingToolbar(rootView)

        imageView = rootView.findViewById(R.id.thumbnail_image_header)
        movieContentDetail = rootView.findViewById(R.id.movie_content_detail)

        nameOfMovie = movieContentDetail.findViewById(R.id.title)
        movieScore = movieContentDetail.findViewById(R.id.userrating)
        movieoverview = movieContentDetail.findViewById(R.id.movieoverview)
        recyclerViewCast = movieContentDetail.findViewById(R.id.recycler_view_cast)

        infoButton = movieContentDetail.findViewById(R.id.infoButton)
        rateButton = movieContentDetail.findViewById(R.id.rateButton)
        viewAll = movieContentDetail.findViewById(R.id.viewAll)

        if (data != null) {
            val movieId = data.getInt("id")
            val thumbnail = data.getString("poster_path")
            val movieName = data.getString("original_title")
            val overview = data.getString("overview")
            var rating = data.getDouble("vote_average")

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

            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = RecyclerView.HORIZONTAL
            recyclerViewCast.layoutManager = layoutManager
        } else {
            Toast.makeText(context, "No API Data", Toast.LENGTH_LONG).show()
        }

        infoButton.setOnClickListener {
            val activity = view?.context as AppCompatActivity1
            Navigation.findNavController(activity, R.id.my_nav_host_fragment).navigate(R.id.MovieInfoFragment, data)
        }

        rateButton.setOnClickListener {
            val activity = view?.context as AppCompatActivity1
            Navigation.findNavController(activity, R.id.my_nav_host_fragment).navigate(R.id.RateMovieFragment, data)
        }

        viewAll.setOnClickListener {
            val activity = view?.context as AppCompatActivity1
            Navigation.findNavController(activity, R.id.my_nav_host_fragment).navigate(R.id.MovieCastCrewFragment, data)
        }

        return rootView
    }

    private fun initCollapsingToolbar(rootView: View) {
        val collapsingToolbarLayout = rootView.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbarLayout.isTitleEnabled = true
        collapsingToolbarLayout.title = ""

        val appBarLayout = rootView.findViewById<AppBarLayout>(R.id.appbar)
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
        try {
            if(BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(context, "Obtain the API KEY first", Toast.LENGTH_SHORT).show();
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
                        val cast: ArrayList<Cast> = response.body()!!.cast
                        val crew: ArrayList<Crew> = response.body()!!.crew

                        adapter = context?.let { CastAdapter(it, cast) }!!
                        recyclerViewCast.adapter = adapter
                        recyclerViewCast.smoothScrollToPosition(0)
                        adapter.notifyDataSetChanged()

//                        val castAdapter = context?.let { CastListAdapter(it, cast) }!!
//                        recyclerViewCast.adapter = castAdapter
//                        recyclerViewCast.smoothScrollToPosition(0)
//                        adapter.notifyDataSetChanged()
//
//                        val crewAdapter = context?.let { CrewListAdapter(it, crew) }!!
//                        recyclerViewCast.adapter = crewAdapter
//                        recyclerViewCast.smoothScrollToPosition(0)
//                        adapter.notifyDataSetChanged()

                    } else {
                        Toast.makeText(context, "Error fetching data!", Toast.LENGTH_SHORT).show();
                    }

                override fun onFailure(call: Call<MovieCreditResponse>, t: Throwable) {
                    Log.d("Error PEPE 0", t.message)
                    Toast.makeText(context, "Error fetching data!", Toast.LENGTH_SHORT).show();
                }
            })
        } catch(e: Exception) {
            Log.d("Error z", e.message)
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
