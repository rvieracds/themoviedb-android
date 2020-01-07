package com.example.themoviedb.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.themoviedb.*
import com.example.themoviedb.fragments.PeopleFragment
import com.example.themoviedb.fragments.PopularFragment
import com.example.themoviedb.fragments.SettingsFragment
import com.example.themoviedb.fragments.TopRatedFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

//    private lateinit var recyclerView: RecyclerView// Extends ViewGroup implements ScrollingView, NestedScrollingChild2
//    private lateinit var adapter: MoviesAdapter
//    private lateinit var swipeContainer: SwipeRefreshLayout // Whenever the user can refresh the contents of a view via a vertical swipe gesture.
//    private var movieList: List<Movie> = ArrayList()

    private lateinit var bottomNavigation: BottomNavigationView
//    private var tabLayout: TabLayout? = null
//    private var viewPager: ViewPager? = null
//    private lateinit var llProgressBar: LinearLayout

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.bottomNavigationMovies -> {
                val fragment = PopularFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottomNavigationPeople -> {
                val fragment = PeopleFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottomNavigationSettings -> {
                val fragment =
                    SettingsFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up navigation bar
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//        // Set up tabs
//        tabLayout = findViewById(R.id.tabLayout)
//        viewPager = findViewById(R.id.viewPager)
//
//        tabLayout!!.addTab(tabLayout!!.newTab().setText("Popular"))
//        tabLayout!!.addTab(tabLayout!!.newTab().setText("Top Rated"))
//        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
//
//
//        val adapter = TabsPagerAdapter(supportFragmentManager, tabLayout!!.tabCount)
//        viewPager!!.adapter = adapter
//        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
//
//        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                viewPager!!.currentItem = tab.position
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab) {}
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        })

        val fragment = TopRatedFragment()
        addFragment(fragment)

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main,menu)
//        return  true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when(item.itemId) {
//            R.id.menu_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
