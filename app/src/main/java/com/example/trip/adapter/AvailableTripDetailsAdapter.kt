package com.example.trip.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.models.SpotDetails
import com.example.trip.models.TripDetails
import kotlinx.android.synthetic.main.list_of_available_trip_detail_card_view.view.*
import android.widget.LinearLayout



class AvailableTripDetailsAdapter(val context: Context, val tripDetails : MutableList<TripDetails>,var spotDetails: SpotDetails,var listener: RecyclerAdapterListener) :
        RecyclerView.Adapter<AvailableTripDetailsAdapter.MyViewHolder>() {


    override fun onBindViewHolder(p0: MyViewHolder, position: Int) {
        val place: TripDetails = tripDetails[position]
        if (spotDetails.type == "Explore Button" && spotDetails.cityName.contains(place.city)) {
            p0.setData(place, position)
        }
        else if (spotDetails.type == place.type && spotDetails.cityName.contains(place.city))
        {
            p0.itemView.setVisibility(View.VISIBLE)

            val linearLayout = LinearLayout(context)
            linearLayout.orientation = LinearLayout.VERTICAL
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(30, 20, 30, 0)
            p0.setData(place,position)
        }
        else{
            p0.itemView.visibility = View.INVISIBLE
            p0.itemView.setLayoutParams(RecyclerView.LayoutParams(0, 0))
        }




    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_of_available_trip_detail_card_view, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tripDetails.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: String

        var currPosition: Int = 0

        init {
            itemView.setOnClickListener {
                itemView.defaultFocusHighlightEnabled=true
                listener.onTrendingPlaceViewClicked(currPosition.toString())
            }
        }

        fun setData(place: TripDetails, pos: Int){

            if(place.type=="Hotel")
               {
                   itemView.typeImage.setImageResource(R.drawable.hotel)
               }
            else if(place.type=="Things to do")
               {
                   itemView.typeImage.setImageResource(R.drawable.things_to_do)

               }
            else
               {
                    itemView.typeImage.setImageResource(R.drawable.restaurant)
               }
                itemView.name.text = place.tripName
                itemView.address.text = place.address
                itemView.description.text = place.description
                this.name = place.tripName
                this.currPosition = pos

        }


    }
}

