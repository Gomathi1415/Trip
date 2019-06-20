package com.example.trip.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.adapter.AvailableTripDetailsAdapter
import com.example.trip.models.SpotDetails
import com.example.trip.models.TripDetails
import android.widget.TextView
import android.widget.Toast


class HotelsDisplayFragments : Fragment(), RecyclerAdapterListener, FilterDialogFragment.FilterCommunicator {


    lateinit var spotDetails: SpotDetails
    var mfilterItemCount: Int = 0
    var filterPrice = 0
    var filterRating =0
    var filterClass = 0
    lateinit var textCartItemCount: TextView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var availableCityRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: AvailableTripDetailsAdapter
    private lateinit var rootView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.display_hotel_name_fragment, container, false)
        return rootView
    }

    override fun onTrendingPlaceViewClicked(position: String) {
        (parentFragment as UserPreferenceSelectionFragment).openDescription(position)
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
            val dialog: FilterDialogFragment =
                FilterDialogFragment.newInstance(filterPrice,filterRating,filterClass)
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

    fun initSpotDetails(spotDetails: SpotDetails) {
        this.spotDetails = spotDetails
    }

    fun setAdaptder(price: String,rating:String,hotelClass :String) {
        availableCityRecyclerView = rootView.findViewById(R.id.avalilableHotelsRecycleView)
        layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        availableCityRecyclerView.layoutManager = layoutManager
        recyclerViewAdapter =
            AvailableTripDetailsAdapter(
                this.context!!,
                TripDetails.Supplier.tripDetails,
                spotDetails,
                "Hotel",
                this,
                price,rating,hotelClass,"0"
            )
        availableCityRecyclerView.adapter = recyclerViewAdapter
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdaptder(filterPrice.toString(),filterRating.toString(),filterClass.toString())
    }


    override fun onDialogMessege(price: String, rating: String, hotelClass: String) {
        if (price == "Rs.10000 Below") {
            filterPrice = 1
        } else if (price == "Rs.10000 - Rs.15000") {
            filterPrice = 2
        } else if (price == "Rs.15000 - Rs.20000") {
            filterPrice = 3
        } else if (price == "Rs.20000 - Rs.30000") {
            filterPrice = 4
        } else if (price == "Above Rs.30000") {
            filterPrice = 5
        } else {
            mfilterItemCount=0
            filterPrice = 0

        }
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
        if (hotelClass == "2 star") {
            filterClass = 1
        } else if (hotelClass == "3 star") {
            filterClass = 2
        } else if (hotelClass == "4 star") {
            filterClass = 3
        } else if (hotelClass == "5 star") {
            filterClass = 4
        }
        else {
            mfilterItemCount=0
            filterClass = 0
        }
        if(price!=" " && mfilterItemCount <3) {
            mfilterItemCount++
        }
        if(rating!=" "&& mfilterItemCount <3) {
            mfilterItemCount++

        }
        if(hotelClass!=" "&& mfilterItemCount <3) {
            mfilterItemCount++

        }

        setAdaptder(filterPrice.toString(),filterRating.toString(),filterClass.toString())
        setupBadge()

    }

}







