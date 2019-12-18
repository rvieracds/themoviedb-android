package com.example.themoviedb

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    var recyclerView: RecyclerView? = null
    private var adapter : MoviesAdapter? = null
    private var apiService : Service? = null
    private var client : Client? = null
    private var call : Call<MoviesResponse>? = null
    var movieList: List<Movie>? = null
    var swipeContainer: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews();

        swipeContainer!!.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer!!.setOnRefreshListener {
            override fun onRefresh() {
                initViews();
                Toast.makeText(MainActivity.this, "Movies Refreshed", Toast.LENGTH_SHORT).show();
            }

        };
    }

    private fun initViews() {
        llProgressBar.visibility = View.VISIBLE

        if(baseContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        } else {
            recyclerView!!.layoutManager = GridLayoutManager(this, 4)
        }

        recyclerView?.itemAnimator = DefaultItemAnimator();
        recyclerView?.adapter = adapter;
        adapter.notifyDataSetChanged()

        loadJSON();
    }

    private fun loadJSON() {
        try{
            if(BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(applicationContext, "Obtain the API KEY first", Toast.LENGTH_SHORT).show();
                llProgressBar.visibility = View.GONE
                return
            }

            apiService?.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN).
                enqueue(object : Callback<MoviesResponse> {
                    override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                        if(response.isSuccessful) {
                            val moviesResponse = response.body()
                            if (moviesResponse?.movies != null) {
                                recyclerView.adapter = MoviesAdapter(moviesResponse.movies!!)
                                recyclerView.smoothScrollToPosition(0)

                                if (swipeContainer?.isRefreshing!!) {
                                    swipeContainer!!.isRefreshing = false
                                }

                                llProgressBar.visibility = View.GONE
                            } else {
                                Toast.makeText(this@MainActivity, "Error fetching data!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this@MainActivity, "Error fetching data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                        Log.d("Error", t.message)
                        Toast.makeText(this@MainActivity, "Error fetching data!", Toast.LENGTH_SHORT).show();
                    }

                })



        } catch(e: Exception) {
            Log.d("Error", e.message)
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun onOptionsItemSelected(MenuItem item) {
        when(item.getItemId()) {
            R.id.menu_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
