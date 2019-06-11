package com.example.trip.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import com.example.trip.*
import com.example.trip.adapter.CityListAdapter
import com.example.trip.models.*
import kotlinx.android.synthetic.main.display_city_name_fragment.*

class DisplaysCityNameFragment : Fragment(), RecyclerAdapterListener {


    lateinit var communicator: Communicator
    lateinit var mapViewAdapterListener: MapViewAdapterListener
    private lateinit var recyclerViewAdapter: CityListAdapter
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var cityList : MutableList<ListOfTrendingPlaces>
     lateinit var type :String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.display_city_name_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        cityList = ListOfTrendingPlaces.Supplier.availablecities
        if(type!="Explore Button")
        {
            searchIcon.setImageResource(R.drawable.near_me)
            kmlimitsearch.setText("Find nearby $type")
            kmlimitsearch.setOnClickListener {

                mapViewAdapterListener = activity as MapViewAdapterListener
                mapViewAdapterListener.openMapListener(type)
            }


        }

            search(this.context!!)
            layoutManager = LinearLayoutManager(activity)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            cityNamesRecyclerView.layoutManager = layoutManager
            recyclerViewAdapter = CityListAdapter(this.context!!, ListOfTrendingPlaces.Supplier.recentSearches, this)
            cityNamesRecyclerView.adapter = recyclerViewAdapter



        super.onActivityCreated(savedInstanceState)
    }

    fun search(context : Context)
    {
        var cityAvailable :Boolean = false
        var searchItem :SearchView = activity!!.findViewById(R.id.citySearchBar)
        searchItem.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!cityAvailable)
                    Toast.makeText(context,"No Suggestion Available",Toast.LENGTH_SHORT).show()
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                var matchedCity : MutableList<ListOfTrendingPlaces> = mutableListOf()
                if(newText.length==0){
                    recent.setText("Recent Searches")
                    layoutManager = LinearLayoutManager(activity)
                    layoutManager.orientation = LinearLayoutManager.VERTICAL
                    cityNamesRecyclerView.layoutManager = layoutManager
                    recyclerViewAdapter = CityListAdapter(context,ListOfTrendingPlaces.Supplier.recentSearches,this@DisplaysCityNameFragment)
                    cityNamesRecyclerView.adapter = recyclerViewAdapter
                }
                else
                {

                for(city in cityList) {
                    var temp: String = city.toString()
                    if (temp.toLowerCase().contains(newText.toLowerCase()) && newText.length != 0) {
                        matchedCity.add(city)
                        cityAvailable = true
                        recent.setText("Suggestions")
                    }
                    if (matchedCity != null) {
                        layoutManager = LinearLayoutManager(activity)
                        layoutManager.orientation = LinearLayoutManager.VERTICAL
                        cityNamesRecyclerView.layoutManager = layoutManager
                        recyclerViewAdapter = CityListAdapter(context, matchedCity, this@DisplaysCityNameFragment)
                        cityNamesRecyclerView.adapter = recyclerViewAdapter
                    }

                }


                }
                return true
            }
        })
    }


    override fun onTrendingPlaceViewClicked(city: String) {
        communicator = activity as Communicator
        communicator.cityNameListener(city,type)

            }

    fun changeData(type : String)
    {
        this.type = type
    }
}
