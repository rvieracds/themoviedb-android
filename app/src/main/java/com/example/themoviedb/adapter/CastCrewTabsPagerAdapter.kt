package com.example.themoviedb.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.themoviedb.fragments.CastFragment
import com.example.themoviedb.fragments.CrewFragment

private val TAB_TITLES = arrayOf("Cast", "Crew")

class CastCrewTabsPagerAdapter(fragmentManager: FragmentManager, private val movieId: Int) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CastFragment(movieId)
            1 -> CrewFragment(movieId)
            else -> CastFragment(movieId)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return 2
    }
}