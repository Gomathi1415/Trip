package com.example.trip.models

data class ListOfTrendingPlaces(var title: String)
{
    object Supplier {
        val trendingPlaces = mutableListOf<ListOfTrendingPlaces>(
            ListOfTrendingPlaces("Bangalore,Karnataka"),
            ListOfTrendingPlaces("Munnar,Kerala"),
            ListOfTrendingPlaces("kabini,Karnataka"),
            ListOfTrendingPlaces("Yercaud,TamilNadu"),
            ListOfTrendingPlaces("Murudeshwar,Karnataka"),
            ListOfTrendingPlaces("BR Hills,Karnataka"),
            ListOfTrendingPlaces("Gokarna,Karnataka"),
            ListOfTrendingPlaces("Bellikal,TamilNadu"),
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
            ListOfTrendingPlaces("kabini,Karnataka"),
            ListOfTrendingPlaces("Yercaud,TamilNadu"),
            ListOfTrendingPlaces("Murudeshwar,Karnataka"),
            ListOfTrendingPlaces("BR Hills,Karnataka"),
            ListOfTrendingPlaces("Gokarna,Karnataka"),
            ListOfTrendingPlaces("Bellikal,TamilNadu"),
            ListOfTrendingPlaces("Ooty,TamilNadu"),
            ListOfTrendingPlaces("Thekkady,Kerala"),
            ListOfTrendingPlaces("Chennai,TamilNadu")

        )
    }
}

