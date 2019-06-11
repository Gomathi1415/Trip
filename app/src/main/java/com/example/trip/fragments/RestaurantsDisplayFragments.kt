package com.example.trip.fragments

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.trip.MapViewAdapterListener
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.adapter.AvailableTripDetailsAdapter
import com.example.trip.models.SpotDetails
import com.example.trip.models.TripDetails

class RestaurantsDisplayFragments : Fragment() ,RecyclerAdapterListener{

        lateinit var spotDetails: SpotDetails
        lateinit var recyclerViewAdapterListener: RecyclerAdapterListener
        lateinit var layoutManager: LinearLayoutManager
    lateinit var mapViewAdapterListener: MapViewAdapterListener
    lateinit var mapButton : FloatingActionButton
        lateinit var availableCityRecyclerView: RecyclerView
    private lateinit var rootView:View

    private lateinit var recyclerViewAdapter: AvailableTripDetailsAdapter
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            rootView = inflater.inflate(R.layout.display_restaurant_names_fragment, container, false)
            availableCityRecyclerView = rootView.findViewById(R.id.avalilableRestaurantsRecycleView)
            layoutManager = LinearLayoutManager(context)


            layoutManager.orientation = LinearLayoutManager.VERTICAL

            availableCityRecyclerView.layoutManager = layoutManager
            recyclerViewAdapter =
                AvailableTripDetailsAdapter(this.context!!, TripDetails.Supplier.tripDetails, spotDetails,"Restaurant", this)
            availableCityRecyclerView.adapter = recyclerViewAdapter


            return rootView
        }

    override fun onTrendingPlaceViewClicked(position: String) {
        Toast.makeText(context,"djj",Toast.LENGTH_SHORT).show()

            recyclerViewAdapterListener = activity as RecyclerAdapterListener
            recyclerViewAdapterListener.onTrendingPlaceViewClicked(position)

        }
    fun initSpotDetails(spotDetails: SpotDetails){
        this.spotDetails=spotDetails
    }
    }
