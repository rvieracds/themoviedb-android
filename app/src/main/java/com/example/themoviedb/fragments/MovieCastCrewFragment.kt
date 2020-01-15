package com.example.themoviedb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager

import com.example.themoviedb.R
import com.example.themoviedb.adapter.CastCrewTabsPagerAdapter
import com.example.themoviedb.adapter.TabsPagerAdapter
import com.google.android.material.tabs.TabLayout

class MovieCastCrewFragment : Fragment() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_movie_cast_crew, container, false)
        val data = arguments
        if (data != null) {
            val movieId = data.getSerializable("id") as Int

            // Set up top tabs (Popular / Top Rated)
            tabLayout = rootView.findViewById(R.id.tabLayout)
            viewPager = rootView.findViewById(R.id.viewPager)

            val adapter = CastCrewTabsPagerAdapter(activity!!.supportFragmentManager, movieId)
            viewPager!!.adapter = adapter
            tabLayout!!.setupWithViewPager(viewPager)
        }

        return rootView
    }


}
