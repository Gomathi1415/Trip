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


class HotelsDisplayFragments : Fragment(), RecyclerAdapterListener, FilterDialogFragment.FilterCommunicator {

    lateinit var spotDetails: SpotDetails
    var mfilterItemCount: Int = 0
    var filterItem = 0
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
            var manager = childFragmentManager
            var dialog: FilterDialogFragment =
                FilterDialogFragment.newInstance(filterItem)
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

    fun setAdaptder(price: String) {
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
                price
            )
        availableCityRecyclerView.adapter = recyclerViewAdapter
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdaptder(filterItem.toString())
    }

    override fun onDialogMessege(message: String) {
        if (message == "Rs.10000 Below") {
            mfilterItemCount = 1
            filterItem = 1
        } else if (message == "Rs.10000 - Rs.15000") {
            filterItem = 2
            mfilterItemCount = 1
        } else if (message == "Rs.15000 - Rs.20000") {
            filterItem = 3
            mfilterItemCount = 1
        } else if (message == "Rs.20000 - Rs.30000") {
            filterItem = 4
            mfilterItemCount = 1
        } else if (message == "Above Rs.30000") {
            filterItem = 5
            mfilterItemCount = 1
        } else {
            filterItem = 0
            mfilterItemCount = 0
        }
        setAdaptder(filterItem.toString())
        setupBadge()

    }

}







