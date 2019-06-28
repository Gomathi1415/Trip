package com.example.trip.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import com.example.trip.DisplayFullImageListener
import com.example.trip.R
import com.example.trip.adapter.DescriptionViewPagerAdapter
import com.example.trip.models.Images
import com.example.trip.models.TripDetails


class FullViewFragment :Fragment(),DisplayFullImageListener{
    override fun openImage(position: String, tripPosition: String) {
    }
    lateinit var adapter: DescriptionViewPagerAdapter
    var currentPosition : Int = 0
    var tripPosition :Int =0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =inflater.inflate(R.layout.full_view_fragment,container,false)
        val images = TripDetails.Supplier.tripDetails[tripPosition]
        adapter = DescriptionViewPagerAdapter(context!!,Images.Supplier.tripImage,this,2)
        var fullviewpager = view.findViewById<ViewPager>(R.id.fullviewpager) as ViewPager
        fullviewpager.adapter = adapter
        fullviewpager.setCurrentItem(currentPosition,true)

        return view
    }

    fun currPosition(currentPosition  :Int,tripPosition:Int)
    {
        this.currentPosition = currentPosition
        this.tripPosition =tripPosition
    }

}
