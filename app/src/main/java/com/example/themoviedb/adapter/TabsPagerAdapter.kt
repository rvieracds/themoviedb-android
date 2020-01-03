package com.example.themoviedb.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.themoviedb.PopularFragment
import com.example.themoviedb.TopRatedFragment

class TabsPagerAdapter(fragmentManager: FragmentManager, private var tabCount: Int) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PopularFragment()
            1 -> TopRatedFragment()
            else -> PopularFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}