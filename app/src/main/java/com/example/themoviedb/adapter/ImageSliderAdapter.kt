package com.example.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.themoviedb.R
import com.example.themoviedb.model.MovieImage
import kotlinx.android.synthetic.main.image_slider_item.view.*

class ImageSliderAdapter(private val context: Context, private val backdrops: List<MovieImage>) : PagerAdapter() {

    private var inflater: LayoutInflater? = null
//    private val images = arrayOf(R.drawable.anton, R.drawable.frankjpg, R.drawable.redcharlie, R.drawable.westboundary)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return backdrops.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.image_slider_item, null)
        val url = "https://image.tmdb.org/t/p/w500" + backdrops[position].filePath

        Glide.with(context)
            .load(url)
            .into(view.thumbnail_image_header)

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}