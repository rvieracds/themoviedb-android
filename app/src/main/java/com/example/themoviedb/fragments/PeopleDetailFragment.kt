package com.example.themoviedb.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.BuildConfig
import com.example.themoviedb.R
import com.example.themoviedb.adapter.KnownForAdapter
import com.example.themoviedb.api.Client
import com.example.themoviedb.api.Service
import com.example.themoviedb.model.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleDetailFragment : Fragment() {

    private lateinit var actorName: TextView
    private lateinit var imageView: ImageView
    private lateinit var recyclerViewKnownFor: RecyclerView
    private lateinit var adapter: KnownForAdapter
    private lateinit var infoButton: ImageView
    private lateinit var movieContentDetail: LinearLayout
    private lateinit var llProgressBar: LinearLayout
    private lateinit var actorBio: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_people_detail, container, false)
        val data = arguments

        initCollapsingToolbar(rootView)

        imageView = rootView.findViewById(R.id.thumbnail_image_header)
        actorName = rootView.findViewById(R.id.actorName)
        recyclerViewKnownFor = rootView.findViewById(R.id.recycler_view_knownFor)
        infoButton = rootView.findViewById(R.id.infoButton)
        actorBio = rootView.findViewById(R.id.actorBio)
        llProgressBar = rootView.findViewById(R.id.llProgressBar)

        llProgressBar!!.visibility = View.VISIBLE

        if (data != null) {
            val personId = data.getInt("id")
            val personName = data.getString("name")
            val profilePath = data.getString("profile_path")
            val knownFor = data.getSerializable("known_for") as ArrayList<KnownFor>

            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.load)

            val url = "https://image.tmdb.org/t/p/w500$profilePath"

            Glide.with(this)
                .load(url)
                .apply(requestOptions)
                .into(imageView)

            actorName.text = personName

            loadJSON(personId)

            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = RecyclerView.HORIZONTAL
            recyclerViewKnownFor.layoutManager = layoutManager

            adapter = context?.let { KnownForAdapter(it, knownFor) }!!
            recyclerViewKnownFor.adapter = adapter
            recyclerViewKnownFor.smoothScrollToPosition(0)
            adapter.notifyDataSetChanged()

        } else {
            Toast.makeText(context, "No API Data", Toast.LENGTH_LONG).show()
        }

        infoButton.setOnClickListener {
            val activity = view?.context as AppCompatActivity
            Navigation.findNavController(activity, R.id.my_nav_host_fragment).navigate(R.id.PeopleInfoFragment, data)
        }

        return rootView
    }

    private fun initCollapsingToolbar(rootView: View) {
        val collapsingToolbarLayout = rootView.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbarLayout.isTitleEnabled = true
        collapsingToolbarLayout.title = ""

        val appBarLayout = rootView.findViewById<AppBarLayout>(R.id.appbar)
        appBarLayout.setExpanded(true)

        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.title = "Person Details"
                    collapsingToolbarLayout.setCollapsedTitleTextColor(resources.getColor(
                        R.color.colorAccent
                    ))
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.title = ""
                    isShow = false
                }
            }
        })
    }

    private fun loadJSON(personId: Int) {
        try{
            if(BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(activity?.applicationContext, "Obtain the API KEY first", Toast.LENGTH_SHORT).show();
                llProgressBar.visibility = View.GONE
                return
            }

            val client = Client()
            val apiService = client.getClient()!!.create(Service::class.java)
            val call = apiService.getPersonDetails(personId, BuildConfig.THE_MOVIE_DB_API_TOKEN)

            call.enqueue(object : Callback<PersonDetailsResponse> {
                override fun onResponse(call: Call<PersonDetailsResponse>, response: Response<PersonDetailsResponse>) =
                    if(response.isSuccessful) {
                        val biography = response.body()?.biography
                        actorBio.text = biography!!.replace("\n", "").replace("\r", "")

                        llProgressBar.visibility = View.GONE
                    } else {
                        Toast.makeText(activity?.applicationContext, "Error fetching data!", Toast.LENGTH_SHORT).show()
                    }

                override fun onFailure(call: Call<PersonDetailsResponse>, t: Throwable) {
                    Log.d("Error PEPE 0", t.message)
                    Toast.makeText(activity?.applicationContext, "Error fetching data!", Toast.LENGTH_SHORT).show()
                }
            })

        } catch(e: Exception) {
            Log.d("Error z", e.message)
            Toast.makeText(activity?.applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}
