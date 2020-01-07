package com.example.themoviedb.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.themoviedb.BuildConfig
import com.example.themoviedb.R
import com.example.themoviedb.adapter.MoviesAdapter
import com.example.themoviedb.api.Client
import com.example.themoviedb.api.Service
import com.example.themoviedb.model.Movie
import com.example.themoviedb.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView// Extends ViewGroup implements ScrollingView, NestedScrollingChild2
    private lateinit var adapter: MoviesAdapter
    private lateinit var swipeContainer: SwipeRefreshLayout // Whenever the user can refresh the contents of a view via a vertical swipe gesture.
    private var movieList: List<Movie> = ArrayList()
    private lateinit var llProgressBar: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_top_rated, container, false)

        recyclerView = rootView.findViewById(R.id.recycler_view)
        llProgressBar = rootView.findViewById(R.id.llProgressBar)
        swipeContainer = rootView.findViewById(R.id.main_content)

//        // Set up tabs
//        tabLayout = rootView.findViewById(R.id.tabLayout)
//        viewPager = rootView.findViewById(R.id.viewPager)
//
//        tabLayout!!.addTab(tabLayout!!.newTab().setText("Popular"))
//        tabLayout!!.addTab(tabLayout!!.newTab().setText("Top Rated"))
//        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL


//        val adapter = activity?.supportFragmentManager?.let { TabsPagerAdapter(it, tabLayout!!.tabCount) }
////        val adapter = TabsPagerAdapter(childFragmentManager, tabLayout!!.tabCount)
//        viewPager!!.adapter = adapter
//        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
//
//        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                viewPager!!.currentItem = tab.position
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab) {}
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        })

        initViews()

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark)
        swipeContainer.setOnRefreshListener {
            initViews()
            Toast.makeText(activity?.applicationContext, "Movies Refreshed", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    private fun initViews() {
        llProgressBar!!.visibility = View.VISIBLE

        adapter = activity?.applicationContext?.let { MoviesAdapter(it, movieList) }!!

        if(activity?.applicationContext!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView?.layoutManager = GridLayoutManager(activity?.applicationContext, 1)
        } else {
            recyclerView?.layoutManager = GridLayoutManager(activity?.applicationContext, 4)
        }

        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        loadJSON()
    }

    private fun loadJSON() {
        try{
            if(BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(activity?.applicationContext, "Obtain the API KEY first", Toast.LENGTH_SHORT).show();
                llProgressBar.visibility = View.GONE
                return
            }

            val client = Client()
            val apiService = client.getClient()!!.create(Service::class.java)
            val call = apiService.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN)

            call.enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) =
                    if(response.isSuccessful) {
                        val movies: List<Movie> = response.body()!!.getResults()
                        recyclerView.adapter =
                            activity?.applicationContext?.let { MoviesAdapter(it, movies) }
                        recyclerView.smoothScrollToPosition(0)

                        if (swipeContainer.isRefreshing) {
                            swipeContainer.isRefreshing = false
                        }
                        llProgressBar.visibility = View.GONE
                    } else {
                        Toast.makeText(activity?.applicationContext, "Error fetching data!", Toast.LENGTH_SHORT).show();
                    }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("Error PEPE 0", t.message)
                    Toast.makeText(activity?.applicationContext, "Error fetching data!", Toast.LENGTH_SHORT).show();
                }
            })



        } catch(e: Exception) {
            Log.d("Error z", e.message)
            Toast.makeText(activity?.applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }


}
