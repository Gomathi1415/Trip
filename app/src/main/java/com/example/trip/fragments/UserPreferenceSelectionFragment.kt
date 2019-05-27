package com.example.trip.fragments

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.trip.Communicator
import com.example.trip.MapViewAdapterListener
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.adapter.AvailableTripDetailsAdapter
import com.example.trip.adapter.CityListAdapter
import com.example.trip.adapter.TrendingPlaceAdapter
import com.example.trip.models.ImagesOfTrendingPlaces
import com.example.trip.models.ListOfTrendingPlaces
import com.example.trip.models.SpotDetails
import com.example.trip.models.TripDetails
import kotlinx.android.synthetic.main.display_city_name_fragment.*
import kotlinx.android.synthetic.main.display_list_of_trip_detail_activity.*
import kotlinx.android.synthetic.main.displays_city_name_activity.*
import kotlinx.android.synthetic.main.user_preference_selection_fragment.*
import java.text.FieldPosition

class UserPreferenceSelectionFragment :Fragment(), RecyclerAdapterListener
{

    private lateinit var rootView: View
    lateinit var spotDetails: SpotDetails
    var position :Int =0
    lateinit var mapViewAdapterListener: MapViewAdapterListener
    lateinit var availableCityRecyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var mapButton : FloatingActionButton
    private lateinit var recyclerViewAdapter: AvailableTripDetailsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.user_preference_selection_fragment, container, false)
        availableCityRecyclerView = rootView.findViewById(R.id.avalilableCityRecycleView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        cityName.setText(spotDetails.cityName)
        layoutManager = LinearLayoutManager(context)

        layoutManager.orientation = LinearLayoutManager.VERTICAL
        availableCityRecyclerView.layoutManager = layoutManager
        recyclerViewAdapter = AvailableTripDetailsAdapter(this.context!!, TripDetails.Supplier.tripDetails, spotDetails, this)
        availableCityRecyclerView.adapter = recyclerViewAdapter
        mapButton = activity!!.findViewById(R.id.mapButton) as FloatingActionButton
        mapButton.setOnClickListener {
            mapViewAdapterListener = activity as MapViewAdapterListener
            mapViewAdapterListener.openMapListener()
        }
            super.onActivityCreated(savedInstanceState)
        }


    fun changeData(spotDetails: SpotDetails)
    {
        this.spotDetails = spotDetails
    }

    override fun onTrendingPlaceViewClicked(position: String) {
        Toast.makeText(context,position,Toast.LENGTH_SHORT).show()
              this.position = position.toInt()

    }



}