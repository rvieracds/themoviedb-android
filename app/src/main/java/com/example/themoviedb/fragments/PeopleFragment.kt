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
import com.example.themoviedb.adapter.PeopleAdapter
import com.example.themoviedb.api.Client
import com.example.themoviedb.api.Service
import com.example.themoviedb.model.People
import com.example.themoviedb.model.PeopleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView// Extends ViewGroup implements ScrollingView, NestedScrollingChild2
    private lateinit var adapter: PeopleAdapter
    private lateinit var swipeContainer: SwipeRefreshLayout // Whenever the user can refresh the contents of a view via a vertical swipe gesture.
    private var peopleList: List<People> = ArrayList()
    private lateinit var llProgressBar: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_people, container, false)

        recyclerView = rootView.findViewById(R.id.recycler_view)
        llProgressBar = rootView.findViewById(R.id.llProgressBar)
        swipeContainer = rootView.findViewById(R.id.main_content)

        initViews()

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark)
        swipeContainer.setOnRefreshListener {
            initViews()
            Toast.makeText(activity?.applicationContext, "People Refreshed", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    private fun initViews() {
        llProgressBar!!.visibility = View.VISIBLE

        adapter = activity?.applicationContext?.let { PeopleAdapter(it, peopleList) }!!

        if(activity?.applicationContext!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView?.layoutManager = GridLayoutManager(activity?.applicationContext, 1)
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
            val call = apiService.getPopularPeople(BuildConfig.THE_MOVIE_DB_API_TOKEN)

            call.enqueue(object : Callback<PeopleResponse> {
                override fun onResponse(call: Call<PeopleResponse>, response: Response<PeopleResponse>) =
                    if(response.isSuccessful) {
                        val people: List<People> = response.body()!!.getResults()
                        recyclerView.adapter =
                            activity?.applicationContext?.let { PeopleAdapter(it, people) }
                        recyclerView.smoothScrollToPosition(0)

                        if (swipeContainer.isRefreshing) {
                            swipeContainer.isRefreshing = false
                        }
                        llProgressBar.visibility = View.GONE
                    } else {
                        Toast.makeText(activity?.applicationContext, "Error fetching data!", Toast.LENGTH_SHORT).show();
                    }

                override fun onFailure(call: Call<PeopleResponse>, t: Throwable) {
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
