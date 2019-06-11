package com.example.trip.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trip.DescriptionDisplayListener
import com.example.trip.MapViewAdapterListener
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.adapter.AvailableTripDetailsAdapter
import com.example.trip.adapter.TabAdapter
import com.example.trip.models.SpotDetails
import com.example.trip.models.TripDetails
import com.google.android.gms.maps.model.CameraPosition
import kotlinx.android.synthetic.main.user_preference_selection_fragment.*

class UserPreferenceSelectionFragment :Fragment()
{


    private lateinit var rootView: View
    private lateinit var hotelsDisplayFragments: HotelsDisplayFragments
    private lateinit var restaurantsDisplayFragments: RestaurantsDisplayFragments
    private lateinit var tripsDisplayFragments: TripsDisplayFragments
    lateinit var spotDetails: SpotDetails
    lateinit var mapViewAdapterListener: MapViewAdapterListener
    lateinit var mapButton : FloatingActionButton
    var position :Int =0
    lateinit var tabAdapterObj : TabAdapter


    lateinit var recyclerViewAdapterListener: RecyclerAdapterListener
    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.user_preference_selection_fragment, container, false)
        var fab: FloatingActionButton = activity!!.findViewById(R.id.mapButton) as FloatingActionButton
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
        if(spotDetails.type=="Explore Button") {
            initFragments()
            assignSpotDetailsToFragments()
        }
        else if(spotDetails.type=="Hotel")
        {
            tabLayout.visibility = View.GONE
            pager.visibility = View.INVISIBLE
            fragmentScreen.visibility=View.VISIBLE

            hotelsDisplayFragments = HotelsDisplayFragments()
            addFragment(hotelsDisplayFragments)
            hotelsDisplayFragments.initSpotDetails(spotDetails)



        }
        else if(spotDetails.type=="Restaurant")
        {
            pager.visibility = View.INVISIBLE
            fragmentScreen.visibility=View.VISIBLE
            tabLayout.visibility = View.GONE
            restaurantsDisplayFragments = RestaurantsDisplayFragments()
            addFragment(restaurantsDisplayFragments)
            restaurantsDisplayFragments.initSpotDetails(spotDetails)



        }
        else
        {
            pager.visibility = View.INVISIBLE
            fragmentScreen.visibility=View.VISIBLE
            tabLayout.visibility = View.GONE
            tripsDisplayFragments = TripsDisplayFragments()
            addFragment(tripsDisplayFragments)
            tripsDisplayFragments.initSpotDetails(spotDetails)



        }
        super.onActivityCreated(savedInstanceState)
    }

    fun changeData(spotDetails: SpotDetails)
    {
        this.spotDetails = spotDetails
    }

    fun initFragments(){
        hotelsDisplayFragments = HotelsDisplayFragments()
        restaurantsDisplayFragments = RestaurantsDisplayFragments()
        tripsDisplayFragments = TripsDisplayFragments()
        tabAdapterObj.addFragment(hotelsDisplayFragments,"Hotels")
        tabAdapterObj.addFragment(restaurantsDisplayFragments,"Restaurants")
        tabAdapterObj.addFragment(tripsDisplayFragments,"Things To Do")
        pager.adapter = tabAdapterObj
        tabLayout.setupWithViewPager(pager)
    }
    fun addFragment(fragment : Fragment)
    {
        var fragmentTransaction : FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentScreen,fragment).commit()



    }

    fun openDescription(position: String) {
        recyclerViewAdapterListener = activity as RecyclerAdapterListener
        recyclerViewAdapterListener.onTrendingPlaceViewClicked(position)

    }


    fun assignSpotDetailsToFragments(){
        hotelsDisplayFragments.initSpotDetails(spotDetails)
        restaurantsDisplayFragments.initSpotDetails(spotDetails)
        tripsDisplayFragments.initSpotDetails(spotDetails)
    }

}