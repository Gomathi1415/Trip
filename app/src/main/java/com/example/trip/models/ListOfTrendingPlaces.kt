package com.example.trip.models

data class ListOfTrendingPlaces(var title: String)
{
    object Supplier {
        val trendingPlaces = mutableListOf<ListOfTrendingPlaces>(
            ListOfTrendingPlaces("Bangalore,Karnataka"),
            ListOfTrendingPlaces("Munnar,Kerala"),
            ListOfTrendingPlaces("Yercaud,TamilNadu"),
            ListOfTrendingPlaces("Wayanad,Kerala"),
            ListOfTrendingPlaces("Ooty,TamilNadu"),
            ListOfTrendingPlaces("Thekkady,Kerala")
        )
        val recentSearches = mutableListOf<ListOfTrendingPlaces>(
            ListOfTrendingPlaces("Bangalore,Karnataka"),
            ListOfTrendingPlaces("Munnar,Kerala")
        )
        val availablecities= mutableListOf<ListOfTrendingPlaces>(
            ListOfTrendingPlaces("Bangalore,Karnataka"),
            ListOfTrendingPlaces("Munnar,Kerala"),
            ListOfTrendingPlaces("Yercaud,TamilNadu"),
            ListOfTrendingPlaces("Wayanad,Kerala"),
            ListOfTrendingPlaces("Ooty,TamilNadu"),
            ListOfTrendingPlaces("Thekkady,Kerala"),
            ListOfTrendingPlaces("Kodaikanal,TamilNadu"),
            ListOfTrendingPlaces("Chennai,TamilNadu")

        )
    }
}

