package com.example.themoviedb.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.themoviedb.R
import com.example.themoviedb.fragments.PopularMoviesFragment
import com.example.themoviedb.fragments.TopRatedMoviesFragment

private val TAB_TITLES = arrayOf("Popular", "Top Rated")

class TabsPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PopularMoviesFragment()
            1 -> TopRatedMoviesFragment()
            else -> PopularMoviesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return 2
    }
}