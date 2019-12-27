package com.example.themoviedb

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.themoviedb.adapter.MoviesAdapter
import com.example.themoviedb.api.Client
import com.example.themoviedb.api.Service
import com.example.themoviedb.model.Movie
import com.example.themoviedb.model.MoviesResponse
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView// Extends ViewGroup implements ScrollingView, NestedScrollingChild2
    private lateinit var adapter: MoviesAdapter
    private var movieList: List<Movie> = ArrayList()
    private lateinit var swipeContainer: SwipeRefreshLayout // Whenever the user can refresh the contents of a view via a vertical swipe gesture.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        swipeContainer = findViewById(R.id.main_content)
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark)
        swipeContainer.setOnRefreshListener {
            initViews()
            Toast.makeText(this@MainActivity, "Movies Refreshed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews() {
        llProgressBar.visibility = View.VISIBLE

        recyclerView = findViewById(R.id.recycler_view)
        adapter = MoviesAdapter(this@MainActivity, movieList)

        if(baseContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView?.layoutManager = GridLayoutManager(this, 1)
        } else {
            recyclerView?.layoutManager = GridLayoutManager(this, 4)
        }

        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        loadJSON()
    }

    private fun loadJSON() {
        try{
            if(BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(applicationContext, "Obtain the API KEY first", Toast.LENGTH_SHORT).show();
                llProgressBar.visibility = View.GONE
                return
            }

            val client = Client()
            val apiService = client.getClient()!!.create(Service::class.java)
            val call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN)

            call.enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if(response.isSuccessful) {
                        val movies: List<Movie> = response.body()!!.getResults()
                        recyclerView.adapter = MoviesAdapter(this@MainActivity, movies)
                        recyclerView.smoothScrollToPosition(0)
                        if (swipeContainer.isRefreshing) {
                            swipeContainer.isRefreshing = false
                        }
                        llProgressBar.visibility = View.GONE
                    } else {
                        Toast.makeText(this@MainActivity, "Error fetching data!", Toast.LENGTH_SHORT).show();
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("Error PEPE 0", t.message)
                    Toast.makeText(this@MainActivity, "Error fetching data!", Toast.LENGTH_SHORT).show();
                }
            })



        } catch(e: Exception) {
            Log.d("Error z", e.message)
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.getItemId()) {
            R.id.menu_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
