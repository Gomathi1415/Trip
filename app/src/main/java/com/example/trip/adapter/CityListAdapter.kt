package com.example.trip.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.models.ListOfTrendingPlaces
import kotlinx.android.synthetic.main.city_display_card_view.view.*

class CityListAdapter(val context: Context, val trendingplaces : MutableList<ListOfTrendingPlaces>,var listener: RecyclerAdapterListener) :
    RecyclerView.Adapter<CityListAdapter.MyViewHolder>() {



    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val place: ListOfTrendingPlaces = trendingplaces[p1]
        p0.setData(place,p1)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.city_display_card_view, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trendingplaces.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var currCity : String

        init {
            itemView.setOnClickListener {
                listener.onTrendingPlaceViewClicked(currCity)

            }
        }
        fun setData(place: ListOfTrendingPlaces, pos : Int) {
            itemView.city_list_text_view.text = place.title
            this.currCity = place.title


        }
    }

}
