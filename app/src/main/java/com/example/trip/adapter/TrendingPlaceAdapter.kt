package com.example.trip.adapter



import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trip.R
import com.example.trip.RecyclerAdapterListener
import com.example.trip.models.ListOfTrendingPlaces
import kotlinx.android.synthetic.main.trending_place_card_view.view.*

class TrendingPlaceAdapter(val context: Context, val trendingplaces : MutableList<ListOfTrendingPlaces>, val  trendingplacesimage: Array<Int>,var listener:RecyclerAdapterListener) :
    RecyclerView.Adapter<TrendingPlaceAdapter.MyViewHolder>() {



    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val place: ListOfTrendingPlaces = trendingplaces[p1]
        val image = trendingplacesimage[p1]
        p0.setData(place,p1,image)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.trending_place_card_view, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trendingplaces.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var currCity :String

        var currPosition : Int =0
        init {
            itemView.setOnClickListener {
                 listener.onTrendingPlaceViewClicked(currCity)
//
            }
        }
        fun setData(place:ListOfTrendingPlaces, pos : Int, image :Int) {
            itemView.placeName.text = place.title
            itemView.setBackgroundResource(image)
            this.currCity = place.title
            this.currPosition = pos

        }
    }

}
