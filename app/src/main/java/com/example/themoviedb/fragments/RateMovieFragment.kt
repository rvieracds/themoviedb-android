package com.example.themoviedb.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.R
import kotlinx.android.synthetic.main.fragment_rate_movie.*


class RateMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_rate_movie, container, false)

        val toolbar = rootView!!.findViewById(R.id.toolbar) as Toolbar
        val removeRateBtn = rootView!!.findViewById(R.id.removeRateBtn) as ImageView

        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = ""
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setHomeAsUpIndicator(R.drawable.close)

        toolbar.setNavigationOnClickListener { view: View? ->
            findNavController().navigate(R.id.action_RateMovieFragment_to_MovieDetailFragment)
        }

        removeRateBtn.setOnClickListener {
            ratingBar.rating = 0F
        }

        return rootView
    }


}
