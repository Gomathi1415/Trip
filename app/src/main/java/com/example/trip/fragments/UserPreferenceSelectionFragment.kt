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
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trip.MapViewAdapterListener
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.adapter.AvailableTripDetailsAdapter
import com.example.trip.adapter.TabAdapter
import com.example.trip.models.SpotDetails
import com.example.trip.models.TripDetails
import kotlinx.android.synthetic.main.user_preference_selection_fragment.*

class UserPreferenceSelectionFragment :Fragment(), RecyclerAdapterListener
{

    private lateinit var rootView: View
    lateinit var spotDetails: SpotDetails
    var position :Int =0

    lateinit var mapViewAdapterListener: MapViewAdapterListener
    lateinit var availableCityRecyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var mapButton : FloatingActionButton
    lateinit var recyclerViewAdapterListener: RecyclerAdapterListener
    private lateinit var recyclerViewAdapter: AvailableTripDetailsAdapter
    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.user_preference_selection_fragment, container, false)
//        availableCityRecyclerView = rootView.findViewById(R.id.avalilableCityRecycleView)
        var fab: FloatingActionButton =activity!!.findViewById(R.id.mapButton) as FloatingActionButton
        fab.visibility= View.VISIBLE
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var tabAdapterObj = TabAdapter(supportFragmentManager)

        tabAdapterObj.addFragment(Fragment1(),"Hotels")
        tabAdapterObj.addFragment(Fragment2(),"Restaurants")
        tabAdapterObj.addFragment(Fragment3(),"Things To Do")

        pager.adapter = tabAdapterObj
        tabLayout.setupWithViewPager(pager)

        layoutManager = LinearLayoutManager(context)

        layoutManager.orientation = LinearLayoutManager.VERTICAL
        availableCityRecyclerView.layoutManager = layoutManager
        recyclerViewAdapter =
            AvailableTripDetailsAdapter(this.context!!, TripDetails.Supplier.tripDetails, spotDetails, this)
        availableCityRecyclerView.adapter = recyclerViewAdapter
        mapButton = activity!!.findViewById(R.id.mapButton) as FloatingActionButton
        mapButton.setOnClickListener {

                mapViewAdapterListener = activity as MapViewAdapterListener
                mapViewAdapterListener.openMapListener(" ")
            }


        super.onActivityCreated(savedInstanceState)
    }

    fun changeData(spotDetails: SpotDetails)
    {
        this.spotDetails = spotDetails
    }

    override fun onTrendingPlaceViewClicked(position: String) {

                recyclerViewAdapterListener = activity as RecyclerAdapterListener
                recyclerViewAdapterListener.onTrendingPlaceViewClicked(position)

    }

}