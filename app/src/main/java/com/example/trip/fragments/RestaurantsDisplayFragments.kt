package com.example.trip.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.adapter.AvailableTripDetailsAdapter
import com.example.trip.models.SpotDetails
import com.example.trip.models.TripDetails

class RestaurantsDisplayFragments : Fragment() ,RecyclerAdapterListener, RestaurantFilterDialogFragment.RestaurantFilterCommunicator{


    lateinit var spotDetails: SpotDetails

        lateinit var layoutManager: LinearLayoutManager
        lateinit var availableCityRecyclerView: RecyclerView
    private lateinit var rootView:View
    var mfilterItemCount: Int = 0

    var filterRating =0
    var filterDiet = 0
    lateinit var textCartItemCount: TextView

    private lateinit var recyclerViewAdapter: AvailableTripDetailsAdapter
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            rootView = inflater.inflate(R.layout.display_restaurant_names_fragment, container, false)
            return rootView
        }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        menu.clear()
        inflater!!.inflate(R.menu.filter_menu, menu)
        val menuItem = menu.findItem(R.id.filtering)
        val actionView = menuItem.actionView
        textCartItemCount = actionView.findViewById(R.id.filter_badge) as TextView
        actionView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onOptionsItemSelected(menuItem)
            }
        })
        setupBadge()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.filtering) {
            val manager = childFragmentManager
            val dialog: RestaurantFilterDialogFragment =
                RestaurantFilterDialogFragment.newInstance(filterRating,filterDiet)
            dialog.show(manager, "customDialog")
        }
        return true
    }
    private fun setupBadge() {
        if (mfilterItemCount == 0) {
            if (textCartItemCount.getVisibility() != View.GONE) {
                textCartItemCount.setVisibility(View.GONE);
            }
        } else {
            textCartItemCount.setText(mfilterItemCount.toString())
            if (textCartItemCount.getVisibility() != View.VISIBLE) {
                textCartItemCount.setVisibility(View.VISIBLE);
            }
        }
    }

    override fun onTrendingPlaceViewClicked(position: String) {

        (parentFragment as UserPreferenceSelectionFragment).openDescription(position)


    }
    fun initSpotDetails(spotDetails: SpotDetails){
        this.spotDetails=spotDetails
    }
    fun setAdaptder(rating:String,dietType :String) {
        availableCityRecyclerView = rootView.findViewById(R.id.avalilableRestaurantsRecycleView)
        layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        availableCityRecyclerView.layoutManager = layoutManager
        recyclerViewAdapter =
            AvailableTripDetailsAdapter(
                this.context!!,
                TripDetails.Supplier.tripDetails,
                spotDetails,
                "Restaurant",
                this,
                "0",rating,"0",dietType
            )
        availableCityRecyclerView.adapter = recyclerViewAdapter
        setHasOptionsMenu(true)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdaptder(filterRating.toString(),filterDiet.toString())
    }
    override fun onDialogMessege(rating: String, dietType: String) {

        if (rating == "1 and up") {
            filterRating = 1
        } else if (rating == "2 and up") {
            filterRating = 2
        } else if (rating == "3 and up") {
            filterRating = 3
        } else if (rating == "4 and up") {
            filterRating = 4
        }
        else {
            mfilterItemCount=0
            filterRating = 0
        }
        if (dietType == "Veg Only") {
            filterDiet = 1
        } else if (dietType == "Non-Veg Only") {
            filterDiet = 2
        } else if (dietType == "Veg and Non-Veg") {
            filterDiet = 3
        }
        else {
            mfilterItemCount=0
            filterDiet = 0
        }

        if(rating!=" "&& mfilterItemCount <2) {
            mfilterItemCount++

        }
        if(dietType!=" "&& mfilterItemCount <2) {
            mfilterItemCount++

        }

        setAdaptder(filterRating.toString(),filterDiet.toString())
        setupBadge()
    }
    }
