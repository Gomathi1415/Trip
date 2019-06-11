package com.example.trip.fragments


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Filter
import android.widget.SearchView
import android.widget.Toast
import com.example.trip.DescriptionDisplayListener
import com.example.trip.MapViewAdapterListener
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.adapter.AvailableTripDetailsAdapter
import com.example.trip.models.SpotDetails
import com.example.trip.models.TripDetails


class HotelsDisplayFragments : Fragment(), RecyclerAdapterListener {


    lateinit var spotDetails: SpotDetails
    lateinit var recyclerViewAdapterListener: RecyclerAdapterListener
    lateinit var descriptionDisplayListener : DescriptionDisplayListener
    lateinit var layoutManager  : LinearLayoutManager
    lateinit var availableCityRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: AvailableTripDetailsAdapter
    private lateinit var rootView:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.display_hotel_name_fragment, container, false)
        availableCityRecyclerView = rootView.findViewById(R.id.avalilableHotelsRecycleView)
        layoutManager = LinearLayoutManager(context)


        layoutManager.orientation = LinearLayoutManager.VERTICAL

        availableCityRecyclerView.layoutManager = layoutManager
        recyclerViewAdapter =
            AvailableTripDetailsAdapter(this.context!!, TripDetails.Supplier.tripDetails, spotDetails,"Hotel",this)
        availableCityRecyclerView.adapter = recyclerViewAdapter
        setHasOptionsMenu(true)





        return rootView
    }


    override fun onTrendingPlaceViewClicked(position: String) {

            (parentFragment as UserPreferenceSelectionFragment).openDescription(position)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.filter_menu, menu);

         var filter = menu!!.findItem(R.id.action_filter)
        filter.setOnMenuItemClickListener {
            Log.d("tag","fvdvc")
             true
        }


    }


    fun initSpotDetails(spotDetails: SpotDetails){
        this.spotDetails=spotDetails
    }
}
