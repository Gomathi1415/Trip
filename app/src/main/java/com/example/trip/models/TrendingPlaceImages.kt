package com.example.trip.models

import com.example.trip.R


data class ImagesOfTrendingPlaces(var title: Int)
{
    object Supplier {
        val trendingPlacesImage = arrayOf(
            R.drawable.bangalore,
            R.drawable.munnar,
            R.drawable.yercaud,
            R.drawable.wayanad,
            R.drawable.ooty,
            R.drawable.thekkady)
    }


}