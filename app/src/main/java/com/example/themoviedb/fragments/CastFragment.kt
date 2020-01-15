package com.example.themoviedb.fragments


import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CastFragment(private val movieId: Int) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CastListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_cast, container, false)

        recyclerView = rootView.findViewById(R.id.recycler_view_cast)
        initViews()

        return rootView
    }

    private fun initViews() {
//        val data = arguments
//        if (data != null) {
//            val castList = data.getSerializable("cast") as ArrayList<Cast>
//            val movieId = data.getSerializable("id") as Int

            loadJSON(movieId)

//            adapter = activity?.applicationContext?.let { CastListAdapter(it, castList) }!!
//
//            if(activity?.applicationContext!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//                recyclerView?.layoutManager = GridLayoutManager(activity?.applicationContext, 1)
//            }
//
//            recyclerView.itemAnimator = DefaultItemAnimator()
//            recyclerView.adapter = adapter
//            adapter.notifyDataSetChanged()
//        }
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

                        val adapter = context?.let { CastListAdapter(it, cast) }!!
                        recyclerView.adapter = adapter
                        recyclerView.smoothScrollToPosition(0)
                        adapter.notifyDataSetChanged()
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
