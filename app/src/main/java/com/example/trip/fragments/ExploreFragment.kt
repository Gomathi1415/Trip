package com.example.trip.fragments


import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.trip.Communicator
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.adapter.GridAdapter
import com.example.trip.adapter.TrendingPlaceAdapter
import com.example.trip.adapter.ViewPagerAdapter
import com.example.trip.models.ImagesOfTrendingPlaces
import com.example.trip.models.ListOfTrendingPlaces
import kotlinx.android.synthetic.main.explore_fragment.*
import java.util.*


class ExploreFragment  : Fragment(),RecyclerAdapterListener{

    private lateinit var rootView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerViewAdapter: TrendingPlaceAdapter
    lateinit var communicator: Communicator
    lateinit var exploreButton: FloatingActionButton
    var grid: GridView? = null

    lateinit var adapter: ViewPagerAdapter
    lateinit var timer: Timer

    var handler: Handler = Handler()

    var images = arrayOf(R.drawable.back2,R.drawable.back1,R.drawable.back4,R.drawable.back3)
    var backgroundMainText = arrayOf("Best Tourist Destination","Explore your hotels","Find best restaurants","Travel Map")
    var backgroundSubText = arrayOf("Discover amazing tourist places","Discover Cheapest and most Convenien hotels around you","Explore your taste Buds","Convenient and easy to use Travel Map of tour destination")
    var userPreference = arrayOf("Hotel","Things to do", "Restaurant")
    var userPreferenceImage = intArrayOf(R.drawable.hotel_icon, R.drawable.things_to_do_icon, R.drawable.restaurant_icon)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.explore_fragment, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerViewAdapter = TrendingPlaceAdapter(
            this.context!!,
            ListOfTrendingPlaces.Supplier.trendingPlaces,
            ImagesOfTrendingPlaces.Supplier.trendingPlacesImage,
            this
        )
        recyclerView.adapter = recyclerViewAdapter
        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        exploreButton = activity!!.findViewById(R.id.exploreButton) as FloatingActionButton


        adapter = ViewPagerAdapter(context!!, images,backgroundMainText,backgroundSubText)
        viewpager.adapter = adapter

        indicator.setupWithViewPager(viewpager,true)
        createSlideShow()

        exploreButton.setOnClickListener {
            communicator = activity as Communicator
            communicator.response("Explore Button") }

        var adapter = GridAdapter(activity!!, userPreference, userPreferenceImage);
            grid = activity!!.findViewById(R.id.gridView) as GridView
            grid!!.setAdapter(adapter)
           grid!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            communicator = activity as Communicator
            var tripType : String = userPreference[position] //changed
               communicator.response(tripType) }

            super.onActivityCreated(savedInstanceState)

        }

    private fun createSlideShow() {
        timer = Timer()

        timer.scheduleAtFixedRate(
            object : TimerTask() {

                override fun run() {

                    handler.postDelayed(object : Runnable {
                        override fun run() {

                            if (viewpager.currentItem < images.size-1) {
                                viewpager.setCurrentItem(viewpager.currentItem+1)
                            } else {
                                viewpager.setCurrentItem(0)

                            }
                        }


                    }, 4000)


                }

            }, 3000, 5000)


    }

    override fun onTrendingPlaceViewClicked(cityName :String) {
        communicator = activity as Communicator
        communicator.cityNameListener(cityName,"Explore Button")
//        Toast.makeText(context,"trending placed clicked",Toast.LENGTH_SHORT).show()
    }

}



