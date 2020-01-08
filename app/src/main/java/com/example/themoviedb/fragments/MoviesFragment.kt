package com.example.themoviedb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.themoviedb.R
import com.example.themoviedb.adapter.TabsPagerAdapter
import com.google.android.material.tabs.TabLayout

class MoviesFragment : Fragment() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private lateinit var llProgressBar: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_movies, container, false)

        // Set up top tabs (Popular / Top Rated)
        tabLayout = rootView.findViewById(R.id.tabLayout)
        viewPager = rootView.findViewById(R.id.viewPager)

        val adapter = TabsPagerAdapter(activity!!.supportFragmentManager)
        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager)

        return rootView
    }

}
