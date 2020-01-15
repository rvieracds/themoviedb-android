package com.example.themoviedb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.R

class PeopleInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_people_info, container, false)

        val toolbar = rootView!!.findViewById(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = ""
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setHomeAsUpIndicator(R.drawable.close)

        toolbar.setNavigationOnClickListener { view: View? ->
            findNavController().navigate(R.id.action_PeopleInfoFragment_to_PeopleDetailFragment)
        }

        return rootView
    }


}
