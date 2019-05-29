package com.example.trip.fragments


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
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
import com.example.trip.models.ImagesOfTrendingPlaces
import com.example.trip.models.ListOfTrendingPlaces


class ExploreFragment  : Fragment(),RecyclerAdapterListener{

    private lateinit var rootView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerViewAdapter: TrendingPlaceAdapter
    lateinit var communicator: Communicator
    lateinit var exploreButton: FloatingActionButton
    var grid: GridView? = null
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

    override fun onTrendingPlaceViewClicked(cityName :String) {
        communicator = activity as Communicator
        communicator.cityNameListener(cityName,"Explore Button")
//        Toast.makeText(context,"trending placed clicked",Toast.LENGTH_SHORT).show()
    }

}



