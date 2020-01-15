package com.example.themoviedb.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.themoviedb.R
import com.example.themoviedb.adapter.KnownForListAdapter
import com.example.themoviedb.adapter.PeopleAdapter
import com.example.themoviedb.model.KnownFor
import com.example.themoviedb.model.People

class PeopleKnownForFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView // Extends ViewGroup implements ScrollingView, NestedScrollingChild2
    private lateinit var adapter: KnownForListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_people_known_for, container, false)

        recyclerView = rootView.findViewById(R.id.recycler_view)

        initViews()

        return rootView
    }

    private fun initViews() {
        val data = arguments
        if (data != null) {
            val knownForList = data.getSerializable("known_for") as ArrayList<KnownFor>

            adapter = activity?.applicationContext?.let { KnownForListAdapter(it, knownForList) }!!

            if(activity?.applicationContext!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                recyclerView?.layoutManager = GridLayoutManager(activity?.applicationContext, 1)
            }

            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}
