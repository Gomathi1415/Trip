package com.example.trip.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
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
import android.widget.Toast
import com.example.trip.DAO.ImageDAO
import com.example.trip.models.HotelAmenities
import com.example.trip.models.Images


class AvailableTripDetailsAdapter(val context: Context, val tripDetails : MutableList<TripDetails>,var spotDetails: SpotDetails,var type : String,var listener: RecyclerAdapterListener,var price : String,var rating:String,var hotelClass : String,var dietType : String) :
        RecyclerView.Adapter<AvailableTripDetailsAdapter.MyViewHolder>() {

    var isTripAvailable = false
    lateinit var hotel : HotelAmenities
    lateinit var filterHotelClass : HotelAmenities
    var imageDAO: ImageDAO = ImageDAO()


    override fun onBindViewHolder(p0: MyViewHolder, position: Int) {
        val place: TripDetails = tripDetails[position]
        for(index in HotelAmenities.Supplier.hotelAmenities)
        {
            if(index.hotelName==place.tripName) {
                filterHotelClass = index
            }
        }

         if (type == place.type && spotDetails.cityName.contains(place.city)
             && (price=="0" || (price=="1" && place.price.toDouble()<10000.toDouble()) || (price=="2" && place.price.toDouble()>10000.toDouble() && place.price.toDouble()<15000.toDouble()) || (price=="3" && place.price.toDouble()>15000.toDouble() && place.price.toDouble()<20000.toDouble())|| (price=="4" && place.price.toDouble()>20000.toDouble() && place.price.toDouble()<30000.toDouble()) || (price=="5" && place.price.toDouble()>30000.toDouble()))
             &&(rating=="0"||(rating=="1" && place.reviews.toDouble()>=1.toDouble()) || (rating=="2" && place.reviews.toInt()>=2) || (rating=="3" && place.reviews.toDouble()>=3.toDouble()) || (rating=="4" && place.reviews.toDouble()>=4.toDouble()))
             &&(hotelClass=="0" || ( filterHotelClass.starType.toInt()==(hotelClass.toInt()+1)))
             &&(dietType=="0" ||(dietType=="1" && place.diet=="Pure Veg")||(dietType=="2" && place.diet=="non-veg")||(dietType=="3" && place.diet=="both"))
         )
        {

            imageDAO.getTripImage(place.id)

            p0.itemView.setVisibility(View.VISIBLE)
            isTripAvailable = true
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
        if(position == tripDetails.size-1 && !isTripAvailable )
        {
            Toast.makeText(context,"No results found",Toast.LENGTH_SHORT).show()
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
                listener.onTrendingPlaceViewClicked(currPosition.toString())
            }
        }

        fun setData(place: TripDetails, pos: Int){

            itemView.name.text = place.tripName
                itemView.address.text = place.address
                itemView.description.text = place.description
                this.name = place.tripName
                this.currPosition = pos
            val data:ByteArray = Images.Supplier.tripImage[0].images
            val bmp = BitmapFactory.decodeByteArray(data, 0, data.size)
            var drawable  : Drawable =  BitmapDrawable(Resources.getSystem(),bmp)
            itemView.typeImage.setImageDrawable(drawable)
            itemView.review.text = place.reviews.toString()+" - "+place.no_of_people_reviewed
            if(place.type=="Restaurant")
            {
                itemView.starType.visibility=View.VISIBLE
                if(place.diet=="both")
                    itemView.starType.setText("Veg and Non-Veg")
                else
                {
                    itemView.starType.setText(place.diet)
                }
            }
            else
            {
                itemView.starType.visibility=View.GONE
            }
            if(type=="Hotel") {
                itemView.rupee.maxHeight = 24
                itemView.rupee.maxWidth = 24
                itemView.price.textSize = 20F
                itemView.rupee.visibility = View.VISIBLE
                itemView.price.visibility = View.VISIBLE
                itemView.price.text = place.price
                itemView.rupee.setImageResource(R.drawable.rupee)

                for (index in HotelAmenities.Supplier.hotelAmenities) {
                    if (index.hotelName == place.tripName) {
                        hotel = index
                    }
                }
                if (hotel.starType.toInt() > 0) {
                    itemView.starType.visibility = View.VISIBLE
                    itemView.star.visibility=View.VISIBLE
                    itemView.starType.setText(hotel.starType.toString())
                } else {
                    itemView.starType.visibility = View.GONE
                    itemView.star.visibility=View.GONE
                }
            }
        }

    }
}

