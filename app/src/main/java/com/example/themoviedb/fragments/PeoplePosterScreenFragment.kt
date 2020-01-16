package com.example.themoviedb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.themoviedb.R

class PeoplePosterScreenFragment : Fragment() {

    private lateinit var poster: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_people_poster_screen, container, false)
        val data = arguments

        poster = rootView.findViewById(R.id.poster)

        if (data != null) {
            val posterId = data.getString("profile_path")
            val url = "https://image.tmdb.org/t/p/w500$posterId"

            Glide.with(this)
                .load(url)
                .into(poster)
        }

        val toolbar = rootView!!.findViewById(R.id.toolbar) as Toolbar
        (activity as androidx.appcompat.app.AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as androidx.appcompat.app.AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as androidx.appcompat.app.AppCompatActivity?)!!.supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { view: View? ->
            findNavController().navigate(R.id.action_PeoplePosterScreenFragment_to_PeopleDetailFragment)
        }

        return rootView
    }


}
