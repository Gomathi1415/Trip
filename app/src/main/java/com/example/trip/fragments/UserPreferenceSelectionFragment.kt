package com.example.trip.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trip.MapViewAdapterListener
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.adapter.TabAdapter
import com.example.trip.models.SpotDetails
import kotlinx.android.synthetic.main.user_preference_selection_fragment.*

class UserPreferenceSelectionFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var hotelsDisplayFragments: HotelsDisplayFragments
    private lateinit var restaurantsDisplayFragments: RestaurantsDisplayFragments
    private lateinit var tripsDisplayFragments: TripsDisplayFragments
    lateinit var spotDetails: SpotDetails
    lateinit var mapViewAdapterListener: MapViewAdapterListener
    lateinit var mapButton: FloatingActionButton
    var position: Int = 0
    lateinit var cityName: String
    lateinit var tabAdapterObj: TabAdapter

    lateinit var recyclerViewAdapterListener: RecyclerAdapterListener
    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.user_preference_selection_fragment, container, false)
        val fab: FloatingActionButton =
            activity!!.findViewById<FloatingActionButton>(R.id.mapButton) as FloatingActionButton
        fab.visibility = View.VISIBLE
        mapButton = activity!!.findViewById(R.id.mapButton) as FloatingActionButton
        mapButton.setOnClickListener {

            mapViewAdapterListener = activity as MapViewAdapterListener
            mapViewAdapterListener.openMapListener(" ")
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        tabAdapterObj = TabAdapter(childFragmentManager)
        if (spotDetails.type == "Explore Button") {
            initFragments()
            assignSpotDetailsToFragments()
        } else if (spotDetails.type == "Hotel") {
            tabLayout.visibility = View.GONE
            pager.visibility = View.INVISIBLE
            fragmentScreen.visibility = View.VISIBLE
            hotelsDisplayFragments = HotelsDisplayFragments()
            addFragment(hotelsDisplayFragments)
            hotelsDisplayFragments.initSpotDetails(spotDetails)

        } else if (spotDetails.type == "Restaurant") {
            pager.visibility = View.INVISIBLE
            fragmentScreen.visibility = View.VISIBLE
            tabLayout.visibility = View.GONE
            restaurantsDisplayFragments = RestaurantsDisplayFragments()
            addFragment(restaurantsDisplayFragments)
            restaurantsDisplayFragments.initSpotDetails(spotDetails)

        } else {
            pager.visibility = View.INVISIBLE
            fragmentScreen.visibility = View.VISIBLE
            tabLayout.visibility = View.GONE
            tripsDisplayFragments = TripsDisplayFragments()
            addFragment(tripsDisplayFragments)
            tripsDisplayFragments.initSpotDetails(spotDetails)

        }
        (activity as AppCompatActivity).supportActionBar!!.setTitle(cityName)
        super.onActivityCreated(savedInstanceState)
    }

    fun changeData(spotDetails: SpotDetails, cityName: String) {
        this.spotDetails = spotDetails
        this.cityName = cityName
    }

    fun initFragments() {
        hotelsDisplayFragments = HotelsDisplayFragments()
        restaurantsDisplayFragments = RestaurantsDisplayFragments()
        tripsDisplayFragments = TripsDisplayFragments()
        tabAdapterObj.addFragment(hotelsDisplayFragments, "Hotels")
        tabAdapterObj.addFragment(restaurantsDisplayFragments, "Restaurants")
        tabAdapterObj.addFragment(tripsDisplayFragments, "Things To Do")
        pager.adapter = tabAdapterObj
        tabLayout.setupWithViewPager(pager)
    }

    fun addFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentScreen, fragment).commit()
    }

    fun openDescription(position: String) {
        recyclerViewAdapterListener = activity as RecyclerAdapterListener
        recyclerViewAdapterListener.onTrendingPlaceViewClicked(position)
    }

    fun assignSpotDetailsToFragments() {
        hotelsDisplayFragments.initSpotDetails(spotDetails)
        restaurantsDisplayFragments.initSpotDetails(spotDetails)
        tripsDisplayFragments.initSpotDetails(spotDetails)
    }
}